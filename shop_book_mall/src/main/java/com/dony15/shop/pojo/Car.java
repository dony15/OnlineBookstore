package com.dony15.shop.pojo;

import org.springframework.stereotype.Component;

/**
 * @author DonY15
 * @description
 * @create 2018\6\21 0021
 */

public class Car extends Product {
    private Long uid;
    private Long pid;
    private Integer count;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", count=" + count +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
