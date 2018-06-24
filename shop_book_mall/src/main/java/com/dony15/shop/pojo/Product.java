package com.dony15.shop.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author DonY15
 * @description 商品实体类
 * @create 2018\6\20 0020
 * @property id 商品编号
 * @property name 商品名称
 * @property price 商品价格
 * @property pnum 商品库存
 * @property productType 商品类型
 * @property description 商品描述
 * @property img_url 商品图片url
 *
 */
@Component("product")
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer pnum;
    private String productType;
    private String description;
    private String img_url;


    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, Integer pnum, String productType, String description, String img_url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pnum = pnum;
        this.productType = productType;
        this.description = description;
        this.img_url = img_url;
    }

    public Product(String name, BigDecimal price, Integer pnum, String productType, String description, String img_url) {
        this.name = name;
        this.price = price;
        this.pnum = pnum;
        this.productType = productType;
        this.description = description;
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pnum=" + pnum +
                ", productType='" + productType + '\'' +
                ", description='" + description + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
