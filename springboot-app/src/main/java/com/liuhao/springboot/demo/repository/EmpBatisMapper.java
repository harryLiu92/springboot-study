package com.liuhao.springboot.demo.repository;

import com.liuhao.springboot.demo.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 13:51
 * @Description:
 **/
@Mapper
public interface EmpBatisMapper {

    @Select("select * from employee")
    public List<Employee> queryAll();

    @Select("select * from employee where id = #{id}")
    public Employee findById(@Param("id") Integer id);

    @Delete("delete from employee where id = #{id}")
    public Integer deleteById(Integer id);

    @Delete("insert employee (`lastName`, `email`, `gender`, `departmentid`, `birth`) " +
            "VALUES(#{employee.lastName}, #{employee.email}, #{employee.gender}, #{employee.departmentid}, #{employee.birth}) ")
    public Integer insert(@Param("employee") Employee employee);

    @Delete(" update employee set lastName = #{employee.lastName}, email = #{employee.email}, " +
            "gender = #{employee.gender}, departmentid = #{employee.departmentid}, birth = #{employee.birth} " +
            " where id = #{employee.id} ")
    public Integer updateById(@Param("employee") Employee employee);

}
