package com.home.web.dao;

import com.home.web.model.UserCustomer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


/**
 * @Author: liuhao
 * @Date: 2018/9/13 10:36
 * @Description:
 **/
@Mapper
public interface UserCustomerDAO {

    @Select("select * from user_customer where openid = #{openid}")
    public UserCustomer findById(String openid);

    @Options(useGeneratedKeys=true,keyProperty="userId")
    @Insert("update user_customer " +
            "set avatarUrl=#{avatarUrl},gender=#{gender},nickName=#{nickName}," +
            "language=#{language},country=#{country},province=#{province},city=#{city},updateTime=#{updateTime} " +
            "where openid = #{openid}")
    public Integer updateByOpenId(UserCustomer userCustomer);

    @Options(useGeneratedKeys=true,keyProperty="userId")
    @Insert("insert into user_customer(openid,avatarUrl,gender,nickName,language,country,province,city,createTime,updateTime) " +
            "values(#{openid},#{avatarUrl},#{gender},#{nickName},#{language},#{country},#{province},#{city},#{createTime},#{updateTime})")
    public Integer insert(UserCustomer userCustomer);
}
