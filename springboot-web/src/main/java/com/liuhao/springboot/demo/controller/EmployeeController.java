package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.dto.DepartmentDTO;
import com.liuhao.springboot.demo.dto.EmployeeDTO;
import com.liuhao.springboot.demo.facade.DepartmentFacade;
import com.liuhao.springboot.demo.facade.EmpFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 14:08
 * @Description:
 * GetMapping 查
 * PutMapping 改
 * PostMapping 增
 * DeleteMapping 删
 **/
@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmpFacade empFacade;

    @Autowired
    private DepartmentFacade departmentFacade;

    @GetMapping("emps")
    public String emps(Model model) {
        List<EmployeeDTO> employees = empFacade.queryAll();
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    @GetMapping("emp")
    public String AddEmpPage(Model model) {

        List<DepartmentDTO> departments;
        departments = departmentFacade.queryAll();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    @PostMapping("emp")
    public String AddEmp(EmployeeDTO employee) {
        logger.info("AddEmp emp:{}", employee);

        Integer result = empFacade.insert(employee);

        logger.info("AddEmp emp:{},result:{}", employee, result);

        return "redirect:/emps";
    }

    @GetMapping("emp/{id}")
    public String editEmp(@PathVariable("id") Integer id, Model model) {
        EmployeeDTO employee = empFacade.findById(id);
        model.addAttribute("emp", employee);

        List<DepartmentDTO> departments = departmentFacade.queryAll();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmployee(EmployeeDTO employee) {

        logger.info("updateEmployee employee:{}", employee);
        Integer result = empFacade.updateById(employee);
        logger.info("updateEmployee employee:{}, result:{}", employee, result);

        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        empFacade.deleteById(id);
        return "redirect:/emps";
    }

}