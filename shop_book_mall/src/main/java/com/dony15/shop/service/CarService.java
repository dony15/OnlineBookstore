package com.dony15.shop.service;

import com.dony15.shop.bean.ShoppingCar;
import com.dony15.shop.pojo.Car;
import org.apache.ibatis.annotations.Param;

/**
 * @author DonY15
 * @description
 * @create 2018\6\21 0021
 */
public interface CarService {

    /**
     * 添加购物车
     * @param uid
     * @param pid
     * @return
     */
    int addToCar(Long uid,Long pid);

    /**
     * 查看当前用户的购物车
     * @param uid
     * @return
     */
    ShoppingCar selectToCar(Long uid);

    /**
     * 删除指定商品
     * @param uid
     * @param pid
     * @return
     */
    int deleteOneToCar(Long uid,Long pid);

    /**
     * 编辑购物车,与清空连用,一锤子买卖用insert
     * @param uid
     * @param pids
     * @param count
     * @return
     */
    int editToCar(Long uid,Long[] pids,Integer[] count);

    /**
     * 清空购物车
     * @param uid
     * @return
     */
    int clearToCar(Long uid);

}
