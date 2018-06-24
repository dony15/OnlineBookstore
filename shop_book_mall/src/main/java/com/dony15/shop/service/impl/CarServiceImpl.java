package com.dony15.shop.service.impl;

import com.dony15.shop.bean.ShoppingCar;
import com.dony15.shop.mapper.CarDao;
import com.dony15.shop.pojo.Car;
import com.dony15.shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\21 0021
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public int addToCar(Long uid, Long pid) {
        //是insert还是update不一定
        Car car= carDao.selectOneToCar(uid,pid);
        int result=0;
        if(car==null){
            //insert
            result=carDao.insertToCar(uid,pid);
        }else{
            //update
            result=carDao.updateToCar(uid,pid,car.getCount()+1);
        }
        return result;
    }

    @Override
    public ShoppingCar selectToCar(Long uid) {
        System.out.println("--------------selectToCar-------------------");
        List<Car> cars = carDao.selectToCarAll(uid);
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setItems(cars);
        shoppingCar.setUid(uid);
        return shoppingCar;
    }

    @Override
    public int deleteOneToCar(Long uid, Long pid) {

        return carDao.deleteOneToCar(uid, pid);
    }



    @Override
    public int editToCar(Long uid,Long[] pids,Integer[] counts) {
        Car car = new Car();
        int result = carDao.clearToCar(uid);
        car.setUid(uid);
        int temp=0;
        for (int i = 0;i < pids.length; i++) {
            if (counts[i]!=0){
                car.setPid(pids[i]);
                car.setCount(counts[i]);
                result = carDao.editToCar(car);
                temp=temp+counts[i];
            }
        }
        System.out.println("添加购物车商品共计"+temp+"件");
        return temp;
    }


    /**
     * 清空购物车
     * @param uid
     * @return
     */
    @Override
    public int clearToCar(Long uid) {
        return carDao.clearToCar(uid);
    }
}
