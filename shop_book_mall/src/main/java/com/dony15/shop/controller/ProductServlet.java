package com.dony15.shop.controller;

import com.dony15.shop.bean.PageInfo;
import com.dony15.shop.pojo.Product;
import com.dony15.shop.service.ProductService;
import com.dony15.shop.utils.FileUpLoadUtils;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\20 0020
 */
@Controller
public class ProductServlet {
    @Autowired
    private ProductService productService;
    @Resource
    private Product product;

    /**
     * 跳转list页面时提供默认数据的方法
     * @param req
     * @return
     */
    private String goListPageList(HttpServletRequest req){
        List<Product> products = productService.selectProduct(null, null, null, null, null);
        req.setAttribute("products",products);
        return "products/list";
    }

    /**
     * 添加商品
     * @param name
     * @param price
     * @param pnum
     * @param productType
     * @param description
     * @param img_url
     * @return
     */
    @RequestMapping("admin/insertProduct")
    public String insertProduct(
            @RequestParam("name")String name,
            @RequestParam("price")String price,
            @RequestParam("pnum")String pnum,
            @RequestParam("productType")String productType,
            @RequestParam("description")String description,
            @RequestParam("img_url")String img_url,
            HttpServletRequest req
    ){ product.setName(name);
       product.setPrice(new BigDecimal(price));
       product.setProductType(productType);
       product.setPnum(Integer.parseInt(pnum));
       product.setImg_url(img_url);
       product.setDescription(description);
        int influence = productService.insertProduct(product);
        if (influence>0){
            return goListPageList(req);
        }else {
            return "products/error";
        }
    }

    /**
     * 图片上传
     * @param req
     * @param resp
     * @param file
     * @throws IOException
     */
    @RequestMapping("/fileupload")
    public void fileupload(
            HttpServletRequest req,
            HttpServletResponse resp,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        System.out.println("***********进入文件上传方法**************");
        String src = FileUpLoadUtils.fileUpLoad(req, file, false, "/oimages");
        System.out.println("****************上传完毕,开始返回:"+src+"*********************");
        resp.getWriter().write(src);
    }

    /**
     * admin模糊查询后台商品列表
     * @param id
     * @param name
     * @param productType
     * @param minPrice
     * @param maxPrice
     * @param req
     * @return
     */
    @RequestMapping("admin/selectProductByadmin")
    public String selectProductByadmin(
            @RequestParam(value = "id",required = false,defaultValue = "-1") String id,
            @RequestParam(value = "name",required = false,defaultValue = "null") String name,
            @RequestParam(value = "category",required = false,defaultValue = "null") String productType,
            @RequestParam(value = "minPrice",required = false,defaultValue = "0") String  minPrice,
            @RequestParam(value = "maxPrice",required = false,defaultValue = "99999") String maxPrice,
            HttpServletRequest req
    ){
        if ("null".equals(name)){name=null;}
        if ("null".equals(productType)){productType=null;}

        List<Product> products = productService.selectProduct(Long.parseLong(id), name, productType, Integer.parseInt(minPrice), Long.parseLong(maxPrice));
        req.setAttribute("products",products);
        return "products/list";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("admin/goeditPage")
    public String goeditPage(
            @RequestParam(value = "id") String id,
            HttpServletRequest request){
        Product product = productService.selectProductById(Long.parseLong(id));
        request.setAttribute("product",product);
        return "products/edit";
    }

    /**
     * 编辑指定商品
     * @param id
     * @param name
     * @param price
     * @param pnum
     * @param productType
     * @param description
     * @param img_url
     * @return
     */
    @RequestMapping("admin/updateProduct")
    public String updateProduct( @RequestParam(value = "id") String id,
                                 @RequestParam("name")String name,
                                 @RequestParam("price")String price,
                                 @RequestParam("pnum")String pnum,
                                 @RequestParam("category")String productType,
                                 @RequestParam("description")String description,
                                 @RequestParam("img_url")String img_url,
                                 HttpServletRequest request
                                 ){
        Product product = productService.selectProductById(Long.parseLong(id));
        if (name!=null&&name!=""){
            product.setName(name);
        }
        if (price!=null&&price!=""){
            product.setPrice(new BigDecimal(price));
        }
        if (pnum!=null&&pnum!=""){
            product.setPnum(Integer.parseInt(pnum));
        }
        if (productType!=null&&productType!=""){
            product.setProductType(productType);
        }
        if (description!=null&&description!=""){
            product.setDescription(description);
        }
        if (img_url!=null&&img_url!=""){
            product.setImg_url(img_url);
        }
        int i = productService.updateProduct(product);
        return i>0?goListPageList(request):"products/error";
    }

    /**
     * 删除指定产品
     * @param id
     * @param req
     * @return
     */
    @RequestMapping("admin/deleteProduct")
    public String deleteProduct(@RequestParam("id")String id,
                                HttpServletRequest req){
        int i = productService.deleteProduct(Long.parseLong(id));
        return i>0?goListPageList(req):"products/error";
    }

    /**
     * 分页(全部商品)
     * @param request
     * @return
     */
    @RequestMapping("/goproductList1")
    public String goproductList1(HttpServletRequest request){
        List<Product> products = productService.selectProduct(null, null, null, null, null);
        request.setAttribute("products",products);
        return "product_list";
    }

    /**
     * 分页查询
     * @param pageNo 第 pageNo 页
     * @param pageSize 每页 Size 容量
     * @param response
     * @throws IOException
     */
    @RequestMapping("/showProductPage")
    public void showProductPage(
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws IOException {
//        System.out.println("分页打印"+pageNo+"  "+pageSize);
        response.setContentType("text/html;charset=utf-8");
        PageInfo<Product> pageInfo=productService.selectProductLimit(pageNo,pageSize);
        response.getWriter().print(new Gson().toJson(pageInfo));
    }



    @RequestMapping("/goproductList2")
    public String goproductList2(HttpServletRequest request,
                                @RequestParam("category") String productType){
        System.out.println("goproductList2的类型:"+productType);
        List<Product> products = productService.selectProductByType(productType);
        if (products!=null){
            request.setAttribute("products",products);
        }else{
            List<Product> products1 = productService.selectProduct(null, null, productType, null, null);
            request.setAttribute("products",products1);
        }
        return "product_list2";
    }
    /**
     * 分页查询
     * @param category 类型
     * @param pageNo 第 pageNo 页
     * @param pageSize 每页 Size 容量
     * @param response
     * @throws IOException
     */
    @RequestMapping("/showProductPage2")
    public void showProductPage2(
            @RequestParam(value = "category") String category,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            HttpServletResponse response
    ) throws IOException {
        System.out.println("分页打印"+pageNo+"  "+pageSize+" "+category);
        response.setContentType("text/html;charset=utf-8");
        PageInfo<Product> pageInfo=productService.selectProductLimit2(category,pageNo,pageSize);
        if (pageInfo!=null){
        response.getWriter().print(new Gson().toJson(pageInfo));
        }
    }


    /**
     * 跳转指定商品详情
     * @param pid
     * @param request
     * @return
     */
    @RequestMapping("/goProductInfo")
    public String goProductInfo(@RequestParam("pid")String pid,
                                HttpServletRequest request
                                ){
        Product product = productService.selectProductById(Long.parseLong(pid));
        request.setAttribute("product",product);
        return "product_info";
    }

    /**
     * 查看所有商品
     * @param request
     * @return
     */
//    @RequestMapping("/selectProductAll")
//    public String selectProductAll(HttpServletRequest request){
//        List<Product> products = productService.selectAllProduct();
//        request.setAttribute("products",products);
//        return "product_list";
//    }
//
//    /**
//     * 查看指定类型商品
//     * @param request
//     * @param productType
//     * @return
//     */
//    @RequestMapping("/selectProductByType")
//    public String selectProductByType(HttpServletRequest request,@RequestParam("category")String productType){
//        List<Product> products = productService.selectProductByType(productType);
//        request.setAttribute("products",products);
//        return "product_list";
//    }

}
