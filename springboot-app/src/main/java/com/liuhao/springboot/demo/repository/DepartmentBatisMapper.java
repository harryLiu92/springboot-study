package com.liuhao.springboot.demo.repository;

import com.liuhao.springboot.demo.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:04
 * @Description:
 **/
@Mapper
public interface DepartmentBatisMapper {

    @Select("select * from department")
    public List<Department> queryAll();

    @Select("select * from department where id = #{id}")
    public Department findById(Integer id);
}
