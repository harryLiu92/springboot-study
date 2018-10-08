package com.home.web.model;

import com.home.web.dto.BaseObject;

import java.util.Date;

/**
 * Administrator liuhao
 * 2018/9/25 22:44
 **/
public class Goods extends BaseObject {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 说明
     */
    private String desc;
    /**
     * '售价'
     */
    private Double sellingPrice;
    /**
     * 成本价
     */
    private Double costPrice;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 折扣价格
     */
    private Double discountPrice;
    /**
     * 商品图像
     */
    private String imageUrl;
    /**
     * 0-停售,1-在售,9-已售完
     */
    private Integer status;
    /**
     * 销售额
     */
    private Integer sales;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 配送方式:0-到店自提,1-急速送货到家,2-送货到家
     */
    private Integer delivery;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
