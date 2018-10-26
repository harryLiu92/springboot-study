package com.liuhao.springboot.demo.service;

import com.liuhao.springboot.demo.dto.DepartmentDTO;
import com.liuhao.springboot.demo.model.Department;
import com.liuhao.springboot.demo.repository.DepartmentBatisMapper;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
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

    public List<DepartmentDTO> queryAll() {
        List<Department> list = departmentBatisMapper.queryAll();

        List<DepartmentDTO> result = Lists.newArrayList();
        for (Department department : list) {
            DepartmentDTO dto = new DepartmentDTO();
            BeanUtils.copyProperties(department, dto);
            result.add(dto);
        }

        return result;
    }

    public DepartmentDTO findById(Integer id) {
        Department department = departmentBatisMapper.findById(id);

        DepartmentDTO dto = new DepartmentDTO();
        BeanUtils.copyProperties(department, dto);

        return dto;
    }
}
