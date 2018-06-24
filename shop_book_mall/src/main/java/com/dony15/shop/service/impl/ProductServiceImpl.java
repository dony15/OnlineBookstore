package com.dony15.shop.service.impl;

import com.dony15.shop.bean.PageInfo;
import com.dony15.shop.mapper.ProductDao;
import com.dony15.shop.pojo.Product;
import com.dony15.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DonY15
 * @description
 * @create 2018\6\20 0020
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    @Override
    public List<Product> selectProduct(Long id, String name, String productType, Integer minPrice, Long maxPrice) {
        if (name!=null){
            name="%"+name+"%";
        }
        if (productType!=null){
            productType="%"+productType+"%";
        }
        if (minPrice==null||minPrice==0){

            minPrice=0;
        }
        if (maxPrice==null||maxPrice==0){

            maxPrice=99999L;
        }
        return productDao.selectProduct(id, name, productType, minPrice, maxPrice);

    }

    @Override
    public Product selectProductById(Long id) {
        return productDao.selectProductById(id);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int deleteProduct(Long id) {
        return productDao.deleteProduct(id);
    }


    @Override
    public PageInfo<Product> selectProductLimit(Integer pageNo, Integer pageSize) {
        PageInfo<Product> pageInfo=new PageInfo<>();

        int start=(pageNo-1)*pageSize;
        List<Product> items=productDao.selectProductLimit(start,pageSize);
        long count=productDao.selectCound();
        long pageCount= ((count/pageSize)+(count%pageSize==0?0:1));
        pageInfo.setItems(items);
        pageInfo.setPageCount(pageCount);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);

        return pageInfo;
    }

    @Override
    public PageInfo<Product> selectProductLimit2(String productType, Integer pageNo, Integer pageSize) {
        PageInfo<Product> pageInfo=new PageInfo<>();

        int start=(pageNo-1)*pageSize;
        List<Product> items=productDao.selectProductLimit2(productType,start,pageSize);
        long count=productDao.selectCoundByType(productType);
        long pageCount= ((count/pageSize)+(count%pageSize==0?0:1));
        pageInfo.setItems(items);
        pageInfo.setPageCount(pageCount);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);

        return pageInfo;
    }

    @Override
    public List<Product> selectProductByType(String productType) {
        List<Product> products = productDao.selectProductByType(productType);
        return products;
    }
//
//    @Override
//    public List<Product> selectAllProduct() {
//        return productDao.selectAllProduct();
//    }

//    @Override
//    public List<Product> selectProductByType(String productType,Integer pageNo, Integer pageSize) {
//        return productDao.selectProductByType(productType,pageNo,pageSize);
//    }


}
