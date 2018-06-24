package com.dony15.shop.service;

import com.dony15.shop.pojo.Order;
import com.dony15.shop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    /**
     * 生成订单(注意生成订单时需要清空购物车)
     * @param id
     * @param receiverAddress
     * @param receiverName
     * @param receiverPhone
     * @param uid
     * @return
     */
    Order insertToOrder(String id, String receiverAddress,
                      String receiverName, String receiverPhone,
                      Long uid);

    /**
     * 查看一个订单
     * @param id
     * @return
     */
    Order selectOneToOrderBy(String id);

    /**
     * 修改订单的支付状态
     * @param id
     * @return
     */
    int updateToOrderstate(String id);

    /**
     * 根据用户查询订单
     * @param uid
     * @return
     */
    List<Order> selectOneToOrderByUser(Long uid);

    /**
     * 删除指定订单
     * @param id
     * @return
     */
    int deleteOrder(String id);

}
