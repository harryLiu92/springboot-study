package com.liuhao.springboot.demo.facade;

import com.liuhao.springboot.demo.dto.EmployeeDTO;
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
public interface EmpFacade {

    public List<EmployeeDTO> queryAll();


    public EmployeeDTO findById(Integer id);

    public Integer deleteById(Integer id);

    public Integer insert(EmployeeDTO employee);

    public Integer updateById(EmployeeDTO employee);
}
