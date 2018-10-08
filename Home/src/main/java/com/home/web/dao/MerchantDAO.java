package com.home.web.dao;

import com.home.web.model.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Administrator liuhao
 * 2018/9/21 22:22
 **/
@Mapper
public interface MerchantDAO {

    /**
     * 查询距离最近的商户
     * @return
     */
    @Select("select * from merchant " +
            "where status = 1 and (longitude < #{longitude} + 0.5 or longitude > #{longitude} - 0.5) " +
            "and (latitude < #{latitude} + 0.5 or latitude > #{latitude} - 0.5) ")
    public List<Merchant> queryNearestByLongitudeAndLatitude(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
