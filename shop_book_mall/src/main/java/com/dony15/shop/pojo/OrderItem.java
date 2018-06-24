package com.dony15.shop.pojo;

/**
 * @author DonY15
 * @description 订单详情
 * @create 2018\6\22 0022
 */
public class OrderItem extends Car {
    private String order_id;
//    private Long pid;
//    private Integer count;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * 设置数据,将购物车数据(包含商品数据)放在订单条目中
     * @param car
     */
    public void setData(Car car){
        this.setId(car.getId());
        this.setUid(car.getUid());
        this.setPid(car.getPid());
        this.setCount(car.getCount());
        this.setProductType(car.getProductType());
        this.setPrice(car.getPrice());
        this.setPnum(car.getPnum());
        this.setName(car.getName());
        this.setImg_url(car.getImg_url());
        this.setDescription(car.getDescription());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_id='" + order_id + '\'' +
                '}'+super.toString();
    }
}
