package com.dony15.shop.bean;

import com.dony15.shop.pojo.Car;

import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\21 0021
 */
public class ShoppingCar {
    private Long uid;
    private List<Car> items;

    public ShoppingCar() {
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "uid=" + uid +
                ", items=" + items +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<Car> getItems() {
        return items;
    }

    public void setItems(List<Car> items) {
        this.items = items;
    }
}
