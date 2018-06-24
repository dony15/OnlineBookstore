package com.dony15.shop.mapper;

import com.dony15.shop.pojo.Car;
import com.dony15.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarDao {

    /**
     * 第一次商品插入购物车 count=1
     * @param uid
     * @param pid
     * @return
     */
    int insertToCar(@Param("uid") Long uid, @Param("pid")Long pid);

    /**
     * 再次插入时对购物车进行修改 count+1
     * @param uid
     * @param pid
     * @return
     */
    int updateToCar(@Param("uid") Long uid, @Param("pid")Long pid,@Param("count")int count);

    /**
     * 查看购物车中唯一指定的条目
     * @param uid
     * @param pid
     * @return
     */
    Car selectOneToCar(@Param("uid") Long uid, @Param("pid")Long pid);

    /**
     * 查看一个用户的购物车
     * @param uid
     * @return
     */
    List<Car> selectToCarAll(@Param("uid")Long uid);

    /**
     * 删除指定商品
     * @param uid
     * @param pid
     * @return
     */
    int deleteOneToCar(@Param("uid") Long uid, @Param("pid")Long pid);

    /**
     * 清空购物车
     * @param uid
     * @return
     */
    int clearToCar(@Param("uid")Long uid);

    /**
     * 编辑购物车,与清空连用,一锤子买卖用insert
     * @param car
     * @return
     */
    int editToCar(Car car);
}
