package com.home.web.dto;

import java.util.Date;

/**
 * Administrator liuhao
 * 2018/9/26 0:31
 * 商户传输DTO
 **/
public class MerchantDTO extends BaseObject {

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
    private String province;
    private String city;
    private String address;
    private String distance;
    private String duration;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
