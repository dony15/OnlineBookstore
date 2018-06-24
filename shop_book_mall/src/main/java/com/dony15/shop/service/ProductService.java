package com.dony15.shop.service;

import com.dony15.shop.bean.PageInfo;
import com.dony15.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * 添加商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 查看商品(模糊)
     * @param id
     * @param name
     * @param productType
     * @param minPrice
     * @param maxPrice
     * @return
     */
    List<Product> selectProduct(Long id, String name, String productType, Integer minPrice, Long maxPrice);

    /**
     * 通过id查询指定商品
     * @param id
     * @return
     */
    Product selectProductById(Long id);

    /**
     * 修改指定商品
     * @param product
     * @return
     */
    int updateProduct(Product product);


    /**
     * 通过指定id删除商品
     * @param id
     * @return
     */
    int deleteProduct(Long id);

    /**
     * 分页查询Product
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Product> selectProductLimit(Integer pageNo, Integer pageSize);

    /**
     * 分页查询Product2
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Product> selectProductLimit2(String productType, Integer pageNo, Integer pageSize);

    /**
     * 通过类型查找商品
     * @param productType
     * @return
     */
    List<Product> selectProductByType(String productType);

}
