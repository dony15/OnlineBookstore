package com.dony15.shop.mapper;

import com.dony15.shop.pojo.Order;
import com.dony15.shop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\22 0022
 */
public interface OrderDao {


    /**
     * 生成订单(注意生成订单时需要清空购物车)
     * @param id
     * @param prices
     * @param receiverAddress
     * @param receiverName
     * @param receiverPhone
     * @param uid
     * @return
     */
    int insertToOrder(@Param("id") String id,@Param("prices") BigDecimal prices, @Param("receiverAddress")String receiverAddress,@Param("receiverName") String receiverName,@Param("receiverPhone") String receiverPhone,@Param("uid") Long uid);
    int insertToOrderItem(OrderItem orderItem);

    /**
     * 查看一个订单
     * @param id
     * @return
     */
    Order selectOneToOrderBy(@Param("id") String id);

    /**
     * 查看用户订单所有详细信息
     * @param order_id
     * @return
     */
    List<OrderItem> selectToOrderItem(@Param("order_id") String order_id);

    /**
     * 修改订单的支付状态
     * @param id
     * @return
     */
    int updateToOrderstate(@Param("id") String id);

    /**
     * 根据用户查询订单
     * @param uid
     * @return
     */
    List<Order> selectOneToOrderByUser(@Param("uid")Long uid);

    /**
     * 删除指定订单
     * @param id
     * @return
     */
    int deleteOrder(@Param("id")String id);

}
