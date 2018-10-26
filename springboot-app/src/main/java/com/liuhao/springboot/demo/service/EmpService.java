package com.liuhao.springboot.demo.service;

import com.liuhao.springboot.demo.dto.EmployeeDTO;
import com.liuhao.springboot.demo.model.Employee;
import com.liuhao.springboot.demo.repository.EmpBatisMapper;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
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

    public List<EmployeeDTO> queryAll() {
        List<Employee> list = empBatisMapper.queryAll();

        List<EmployeeDTO> result = Lists.newArrayList();
        for (Employee employee : list) {
            EmployeeDTO dto = new EmployeeDTO();
            BeanUtils.copyProperties(employee, dto);
            result.add(dto);
        }

        return result;
    }

    public EmployeeDTO findById(Integer id) {
        Employee employee = empBatisMapper.findById(id);

        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);

        return dto;
    }

    public Integer deleteById(Integer id) {
        return empBatisMapper.deleteById(id);
    }

    public Integer insert(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return empBatisMapper.insert(employee);
    }

    public Integer updateById(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return empBatisMapper.updateById(employee);
    }
}
