package com.lky.concurrent_demo;

import com.lky.concurrent_demo.mapper.GoodsMapper;
import com.lky.concurrent_demo.serviceimpl.GoodsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ConcurrentDemoApplicationTests {

    @Autowired
    private GoodsServiceImpl goodsService;
    @Resource
    private GoodsMapper goodsMapper;
    private final static int PERSON_NUMS = 200;
    private final static int Goods_ID = 2;
    private final static int EACH_NUMS = 3;
    private final static int AMOUNT = 100;
    private final static int VERSION = 0;
    private static CountDownLatch count = new CountDownLatch(PERSON_NUMS);
    private static int successPerson = 0;
    private static int saleOutNums = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(100 + 1);


    @BeforeEach
    void init_env(){
        goodsMapper.initAmount(Goods_ID,AMOUNT);
        goodsMapper.initVersion(Goods_ID,VERSION);
        System.out.println("初始化模拟参数!");
    }

    @Test
    void contextLoads() throws BrokenBarrierException, InterruptedException {
        System.out.println("准备并发....");
        long start = System.currentTimeMillis();
        for (int i = 1; i <= PERSON_NUMS; i++) {
            new Thread(new OneRequest(Goods_ID,EACH_NUMS)).start();
            if (i == PERSON_NUMS){
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }                
            }
            count.countDown();
        }
        cyclicBarrier.await();
        long end = System.currentTimeMillis();
        System.out.println("用时: "+ (end - start));
        System.out.println("成功人数: "+successPerson);
        System.out.println("出售数量: "+saleOutNums * 3);
        System.out.println("当前库存: "+goodsMapper.queryGoodsById(Goods_ID).getAmount());

    }

    public class OneRequest implements Runnable{
        private int id;
        private int nums;
        public OneRequest(int id, int nums) {
            this.id = id;
            this.nums = nums;
        }
        @Override
        public void run() {
            try { count.await(); } catch (InterruptedException e) { e.printStackTrace(); }
            if (goodsService.secKillByVersion(id, nums)){
                synchronized (count){
                    successPerson++;
                    saleOutNums++;
                }
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

