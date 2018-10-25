package com.liuhao.springboot.demo.repository;

import com.liuhao.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 11:28
 * @Description:
 **/
@Mapper
public interface UserBatisMapper {

    @Select("select * from user where name = #{name} and password = #{password}")
    public User findByUserNameAndPassword(@Param("name") String name, @Param("password") String password);
}
