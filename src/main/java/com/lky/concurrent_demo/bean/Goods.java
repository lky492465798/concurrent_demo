package com.lky.concurrent_demo.bean;

import org.springframework.stereotype.Repository;

/**
 * @author: lkycccc11@163.com
 * @create: 2020-02-17 13:22
 **/
@Repository
public class Goods {
    private int id;
    private String name;
    private int amount;
    private int version;

    public Goods(int id, String name, int amount, int version) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", version=" + version +
                '}';
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Goods() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
