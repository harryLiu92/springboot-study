package com.liuhao.springboot.demo.service;

import com.liuhao.springboot.demo.model.Employee;
import com.liuhao.springboot.demo.repository.EmpBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:02
 * @Description:
 **/
@Service
public class EmpService {
    @Autowired
    private EmpBatisMapper empBatisMapper;

    public List<Employee> queryAll() {
        return empBatisMapper.queryAll();
    }

    public Employee findById(Integer id) {
        return empBatisMapper.findById(id);
    }

    public Integer deleteById(Integer id) {
        return empBatisMapper.deleteById(id);
    }

    public Integer insert(Employee employee) {
        return empBatisMapper.insert(employee);
    }

    public Integer updateById(Employee employee) {
        return empBatisMapper.updateById(employee);
    }
}
