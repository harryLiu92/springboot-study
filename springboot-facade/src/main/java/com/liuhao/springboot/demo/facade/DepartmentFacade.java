package com.liuhao.springboot.demo.facade;

import com.liuhao.springboot.demo.dto.DepartmentDTO;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:06
 * @Description:
 **/
public interface DepartmentFacade {

    public List<DepartmentDTO> queryAll();

    public DepartmentDTO findById(Integer id);
}
