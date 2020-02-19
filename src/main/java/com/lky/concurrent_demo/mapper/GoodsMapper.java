package com.lky.concurrent_demo.mapper;

import com.lky.concurrent_demo.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: lkycccc11@163.com
 * @create: 2020-02-17 14:05
 **/
@Mapper
public interface GoodsMapper {

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods queryGoodsById(int id);

    @Update("UPDATE goods SET amount = amount - #{nums} WHERE id = #{id}")
    int secKill_ReduceAmount(int id,int nums);

    @Update("UPDATE goods SET amount = #{nums} WHERE id = #{id}")
    void initAmount(int id,int nums);

    //通过版本号控制
    @Update("UPDATE goods SET amount = amount - #{nums} ,version = version + 1 WHERE id = #{id} AND version = #{curr_version}")
    int secKill_ByVersion(int id,int nums,int curr_version);

    //通过数据库状态控制
    @Update("UPDATE goods SET amount = amount - #{nums} WHERE id = #{id} AND amount - #{nums} >= 0")
    int secKill_ByStatus(int id,int nums);

    @Update("UPDATE goods SET version = #{version} WHERE id = #{id}")
    void initVersion(int id, int version);


}
