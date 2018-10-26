package com.liuhao.springboot.demo.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liuhao.springboot.demo.dto.EmployeeDTO;
import com.liuhao.springboot.demo.facade.EmpFacade;
import com.liuhao.springboot.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/26 10:33
 * @Description:
 **/
@Service(version="1.0.0")
public class EmpFacadeImpl implements EmpFacade {

    @Autowired
    private EmpService empService;

    @Override
    public List<EmployeeDTO> queryAll() {
        return empService.queryAll();
    }

    @Override
    public EmployeeDTO findById(Integer id) {
        return empService.findById(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        return empService.deleteById(id);
    }

    @Override
    public Integer insert(EmployeeDTO employee) {
        return empService.insert(employee);
    }

    @Override
    public Integer updateById(EmployeeDTO employee) {
        return updateById(employee);
    }
}
