package com.home.web.model;

import com.home.web.dto.BaseObject;

import java.util.Date;

/**
 * Administrator liuhao
 * 2018/9/21 22:23
 * 商户
 **/
public class Merchant extends BaseObject {

    private Integer id;
    /**
     * 商户名
     */
    private String name;
    /**
     * 图像
     */
    private String logo;
    /**
     * 商户分数
     */
    private Integer score;
    /**
     * 商户等级
     */
    private Integer level;
    /**
     * 商户描述
     */
    private String desc;
    /**
     * 默认：-1禁用，0停业，1开业
     */
    private Integer status;
    private String province;
    private String city;
    private String address;
    /**
     * 纬度
     */
    private String longitude;
    /**
     * 经度
     */
    private String latitude;
    /**
     * '每日开始营业时间'
     */
    private Date beginOpenTime;
    /**
     * '每日结束营业时间'
     */
    private Date endOpenTime;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getBeginOpenTime() {
        return beginOpenTime;
    }

    public void setBeginOpenTime(Date beginOpenTime) {
        this.beginOpenTime = beginOpenTime;
    }

    public Date getEndOpenTime() {
        return endOpenTime;
    }

    public void setEndOpenTime(Date endOpenTime) {
        this.endOpenTime = endOpenTime;
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
