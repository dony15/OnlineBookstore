package com.dony15.shop.controller;

import com.dony15.shop.bean.ShoppingCar;
import com.dony15.shop.pojo.Product;
import com.dony15.shop.pojo.User;
import com.dony15.shop.service.CarService;
import com.dony15.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\21 0021
 */
@Controller
public class CarServlet {

    @Autowired
    private CarService carService;
    @Autowired
    private ProductService productService;

    /**
     * 前往购物车
     * @param request
     * @return
     */
    @RequestMapping("/goToCar")
    public String goToCar(HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        } else {
            Long uid = user.getUid();
            ShoppingCar shoppingCar = carService.selectToCar(uid);
            request.setAttribute("shoppingCar", shoppingCar);
            return "cart";
        }
    }


    /**
     * 商品添加到购物车中(如: 点击购买)
     *
     * @param pid
     * @param request
     * @return 购物车页面/添加失败页面
     */
    @RequestMapping("/addToCar")
    public String addToCar(@RequestParam("pid") String pid,
                           HttpServletRequest request
    ) {
        System.out.println("*********************测试刷新请求*************");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        } else {
            Long uid = user.getUid();
            int result = carService.addToCar(uid, Long.parseLong(pid));
            if (result > 0) {
                ShoppingCar shoppingCar = carService.selectToCar(uid);
                request.setAttribute("shoppingCar", shoppingCar);
                return "cart";
            } else {
                return "error";
            }
        }
    }

    @RequestMapping("/deleteToCar")
    public String deleteToCar(@RequestParam("uid") String uid,
                              @RequestParam("pid") String pid
    ) {
        //不需要提供单独删除功能,提交时修改即可
        return "";
    }


    /**
     * 编辑购物车-->购物列表
     * @param pids
     * @param counts
     * @param request
     * @return
     */
    @RequestMapping("/editandgolist")
    public String editAndGolist(@RequestParam(value = "pid",required = false,defaultValue = "") Long[] pids,
                                @RequestParam(value = "count",required = false,defaultValue = "") Integer[] counts,
                                HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        }
        Long uid = user.getUid();

        if (pids.equals("")||counts.equals("")){
            carService.clearToCar(uid);
            List<Product> products = productService.selectProduct(null, null, null, null, null);
            request.setAttribute("products",products);
            return "product_list";
        }
        int temp = carService.editToCar(uid, pids, counts);
        List<Product> products = productService.selectProduct(null, null, null, null, null);
        request.setAttribute("products",products);
        return "product_list";
    }

    /**
     * 编辑购物车-->订单
     * @param pids
     * @param counts
     * @param request
     * @return
     */
    @RequestMapping("/editandgoorder")
    public String editandgoorder(@RequestParam(value = "pid",required = false,defaultValue = "") Long[] pids,
                                 @RequestParam(value = "count",required = false,defaultValue = "") Integer[] counts,
                                 HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        }
        Long uid = user.getUid();
        if (pids.equals("")||counts.equals("")){
            carService.clearToCar(uid);
            return "product_list";
        }

        int temp = carService.editToCar(uid, pids, counts);
        ShoppingCar shoppingCar = carService.selectToCar(uid);
        request.setAttribute("shoppingCar", shoppingCar);
        return "order";
    }

}
