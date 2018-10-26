package com.liuhao.springboot.demo.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liuhao.springboot.demo.dto.DepartmentDTO;
import com.liuhao.springboot.demo.facade.DepartmentFacade;
import com.liuhao.springboot.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:06
 * @Description:
 **/
@Service(version="1.0.0")
public class DepartmentFacadeImpl implements DepartmentFacade {

    @Autowired
    private DepartmentService departmentService;

    public List<DepartmentDTO> queryAll() {
        return departmentService.queryAll();
    }

    public DepartmentDTO findById(Integer id) {
        return departmentService.findById(id);
    }
}
