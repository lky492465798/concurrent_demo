package com.lky.concurrent_demo.serviceimpl;

import com.lky.concurrent_demo.bean.Goods;
import com.lky.concurrent_demo.mapper.GoodsMapper;
import com.lky.concurrent_demo.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: lkycccc11@163.com
 * @create: 2020-02-17 14:05
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public synchronized boolean secKill(int id,int nums) {
        if (goodsMapper.queryGoodsById(id).getAmount() >= nums){
            return goodsMapper.secKill_ReduceAmount(id,nums) == 1 ? true : false;
        }
        return false;
    }

    @Override
    public boolean secKillByVersion(int id,int nums) {
        Goods goods = goodsMapper.queryGoodsById(id);
        if (goods.getAmount() < nums){
            return false;
        }

//        if (goodsMapper.secKill_ByVersion(id,nums,goods.getVersion()) > 0){
        if (goodsMapper.secKill_ByStatus(id,nums) > 0){
            return true;
        }
        //削峰填谷
        try { TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10) + 1); } catch (InterruptedException e) { e.printStackTrace(); }
        //CAS实现乐观锁
        return secKillByVersion(id,nums);
    }

}
