package com.home.web.model;

import com.home.web.dto.BaseObject;

import java.util.Date;

/**
 * Administrator liuhao
 * 2018/9/25 22:07
 **/
public class GoodsCatalogue extends BaseObject {
    private Integer id;
    /**
     * '商户id'
     */
    private Integer merchantid;
    /**
     * '商品分类名称'
     */
    private String name;
    /**
     * 1-上架，0-下架
     */
    private Integer status;
    /**
     * '排序'
     */
    private Integer order;
    /**
     * '分组排序'
     */
    private Integer groupOrder;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
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
