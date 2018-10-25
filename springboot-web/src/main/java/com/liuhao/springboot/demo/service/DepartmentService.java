package com.liuhao.springboot.demo.service;

import com.liuhao.springboot.demo.model.Department;
import com.liuhao.springboot.demo.repository.DepartmentBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:06
 * @Description:
 **/
@Service
public class DepartmentService {

    @Autowired
    private DepartmentBatisMapper departmentBatisMapper;

    public List<Department> queryAll() {
        return departmentBatisMapper.queryAll();
    }

    public Department findById(Integer id) {
        return departmentBatisMapper.findById(id);
    }
}
