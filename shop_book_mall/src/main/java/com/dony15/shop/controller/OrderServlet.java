package com.dony15.shop.controller;

import com.dony15.shop.bean.ShoppingCar;
import com.dony15.shop.pojo.Car;
import com.dony15.shop.pojo.Order;
import com.dony15.shop.pojo.User;
import com.dony15.shop.service.CarService;
import com.dony15.shop.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\22 0022
 */

@Controller
public class OrderServlet {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;

    /**
     * 添加订单
     * @param receiverAddress
     * @param receiverName
     * @param receiverPhone
     * @param request
     * @return
     */
   @RequestMapping("/insertToOrder")
   public String insertToOrder(
           @RequestParam("receiverAddress")String receiverAddress,
           @RequestParam("receiverName")String receiverName,
           @RequestParam("receiverPhone")String receiverPhone,
           HttpServletRequest request
    ){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        } else {
            if ("".equals(receiverAddress)||"".equals(receiverName)||"".equals(receiverPhone)){
                return "error";
            }
            String id=System.currentTimeMillis()+""+user.getUid();
            Order order= orderService.insertToOrder(id,
                    receiverAddress, receiverName, receiverPhone,
                    user.getUid());

            request.setAttribute("orderInfo",order);
            return "orderInfo";
        }
   }


    /**
     * 查看订单信息
     * @param request
     * @return
     */
   @RequestMapping("/goOrderlist")
   public String goOrderlist(
           HttpServletRequest request){
       User user = (User) request.getSession().getAttribute("user");
       Long uid = user.getUid();
       List<Order> order = orderService.selectOneToOrderByUser(uid);
       System.out.println("查看订单信息--------------:"+order);
       request.setAttribute("orders",order);

       return "orderlist";
   }

    /**
     * 查看指定订单信息
     * @param request
     * @return
     */
    @RequestMapping("/goOneOrder")
    public String goOneOrder(
            @RequestParam("orderid")String orderid,
            HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Order order1 = orderService.selectOneToOrderBy(orderid);
        List<Order> order=new ArrayList<>();
        order.add(order1);
        System.out.println("查看订单信息--------------:"+order);
        request.setAttribute("orders",order);
        return "orderlist";
    }


    @RequestMapping("/deleteOrder")
    public String deleteOrder(
            @RequestParam("orderid")String orderid,
            HttpServletRequest request
    ){
        orderService.deleteOrder(orderid);
        User user = (User) request.getSession().getAttribute("user");
        Long uid = user.getUid();
        List<Order> order = orderService.selectOneToOrderByUser(uid);
        System.out.println("查看订单信息--------------:"+order);
        request.setAttribute("orders",order);
        return "orderlist";
    }






}
