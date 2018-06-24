package com.dony15.shop.service.impl;

import com.dony15.shop.mapper.CarDao;
import com.dony15.shop.mapper.OrderDao;
import com.dony15.shop.pojo.Car;
import com.dony15.shop.pojo.Order;
import com.dony15.shop.pojo.OrderItem;
import com.dony15.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\22 0022
 */
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CarDao carDao;
    @Override
    public Order insertToOrder(String id, String receiverAddress,
                             String receiverName, String receiverPhone,
                             Long uid) {
        BigDecimal prices =new BigDecimal("0");
        List<Car> cars = carDao.selectToCarAll(uid);
        System.out.println("测试Cars:"+cars);
        List<OrderItem> orderItems=new ArrayList<>();

//        orderItem.setUid(uid);
        for (int i1 = 0; i1 < cars.size(); i1++) {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder_id(id);
            orderItem.setData(cars.get(i1));
            orderDao.insertToOrderItem(orderItem);
//            prices=orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount()));//***用orderItem
            prices=prices.add(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())));//***用orderItem

            orderItems.add(orderItem);
        }


        int i = orderDao.insertToOrder(id, prices, receiverAddress, receiverName, receiverPhone, uid);

        Order order = orderDao.selectOneToOrderBy(id);
        order.setItems(orderItems);
            carDao.clearToCar(uid); //待定位置是否对结果有所影响
        System.out.println("影响:"+i+"行");
        System.out.println("测试OrderServiceImp订单内容:"+order);
        return order;
    }

    /**
     * 查找订单
     * 查找订单条目
     * 将订单条目放入订单中
     * 返回订单
     * @param id
     * @return
     */
    @Override
    public Order selectOneToOrderBy(String id) {
        Order order = orderDao.selectOneToOrderBy(id);
        List<OrderItem> orderItems = orderDao.selectToOrderItem(id);
        order.setItems(orderItems);
        return order;
    }

    /**
     * 更新支付状态
     * @param id
     * @return
     */
    @Override
    public int updateToOrderstate(String id) {
        return orderDao.updateToOrderstate(id);
    }

    /**
     * 查看用户订单
     * @param uid
     * @return
     */
    @Override
    public List<Order> selectOneToOrderByUser(Long uid) {
        return  orderDao.selectOneToOrderByUser(uid);
    }


    @Override
    public int deleteOrder(String id) {
        return  orderDao.deleteOrder(id);
    }


}
