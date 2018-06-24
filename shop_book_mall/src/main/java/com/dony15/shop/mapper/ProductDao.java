package com.dony15.shop.mapper;

import com.dony15.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

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
    List<Product> selectProduct(@Param("id") Long id,
                       @Param("name") String name,
                       @Param("productType") String productType,
                       @Param("minPrice") Integer  minPrice,
                       @Param("maxPrice") Long maxPrice);

    /**
     * 通过id查询指定商品
     * @param id
     * @return
     */
    Product selectProductById(@Param("id") Long id);

    /**
     * 通过类型查找商品
     * @param productType
     * @return
     */
    List<Product> selectProductByType(@Param("productType") String productType);

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
    int deleteProduct(@Param("id")Long id);

    /**
     * 分页查询Product
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Product> selectProductLimit(
                                     @Param("pageNo")Integer pageNo,
                                     @Param("pageSize")Integer pageSize);

    /**
     * 分页查询Product2
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Product> selectProductLimit2(
            @Param("productType")String productType,
            @Param("pageNo")Integer pageNo,
            @Param("pageSize")Integer pageSize);

    /**
     * 总记录数
     * @return
     */
    Long selectCound();

    /**
     * 指定类型记录数
     * @param productType
     * @return
     */
    Long selectCoundByType(@Param("productType")String productType);
//
//    /**
//     * 查看商品列表(全部)
//     * @return
//     */
//    List<Product> selectAllProduct(@Param("pageNo")Integer pageNo,
//                                   @Param("pageSize")Integer pageSize);
//
//
//    /**
//     * 根据商品类型查询商品列表
//     * @param productType
//     * @return
//     */
//    List<Product> selectProductByType(@Param("productType")String productType,
//                                      @Param("pageNo")Integer pageNo,
//                                      @Param("pageSize")Integer pageSize);

}
