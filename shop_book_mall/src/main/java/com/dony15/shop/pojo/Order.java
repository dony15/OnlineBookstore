package com.dony15.shop.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DonY15
 * @description 订单
 * @create 2018\6\22 0022
 */
public class Order {
    /**
     * @id 毫秒数+uid
     * @prices 订单总价格
     * @receiverAddress 收货人地址
     * @receiverName 收货人姓名
     * @receiverPhone 收货人电话
     * @paystate 付款状态 0未付款/1已付款
     * @orderTime 下单时间
     * @uid 用户id
     * @items 订单详情
     */
    private String id;
    private BigDecimal prices;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Integer paystate;
    private String orderTime;
    private Long uid;
    private List<OrderItem> items;

    public Order() {
        this.id = id;
        this.prices = prices;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.paystate = paystate;
        this.orderTime = orderTime;
        this.uid = uid;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", prices=" + prices +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", paystate=" + paystate +
                ", orderTime='" + orderTime + '\'' +
                ", uid=" + uid +
                ", orderItemList=" + items +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Integer getPaystate() {
        return paystate;
    }

    public void setPaystate(Integer paystate) {
        this.paystate = paystate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
