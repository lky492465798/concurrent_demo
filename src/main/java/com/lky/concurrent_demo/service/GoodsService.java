package com.lky.concurrent_demo.service;

public interface GoodsService {

    boolean secKill (int id,int nums);
    boolean secKillByVersion(int id,int nums);

}
