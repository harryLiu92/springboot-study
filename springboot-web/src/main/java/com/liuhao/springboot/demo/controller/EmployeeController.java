package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.model.Department;
import com.liuhao.springboot.demo.model.Employee;
import com.liuhao.springboot.demo.service.DepartmentService;
import com.liuhao.springboot.demo.service.EmpService;
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
    private EmpService empService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("emps")
    public String emps(Model model) {
        List<Employee> employees = empService.queryAll();
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    @GetMapping("emp")
    public String AddEmpPage(Model model) {

        List<Department> departments = departmentService.queryAll();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    @PostMapping("emp")
    public String AddEmp(Employee employee) {
        logger.info("AddEmp emp:{}", employee);

        Integer result = empService.insert(employee);

        logger.info("AddEmp emp:{},result:{}", employee, result);

        return "redirect:/emps";
    }

    @GetMapping("emp/{id}")
    public String editEmp(@PathVariable("id") Integer id, Model model) {
        Employee employee = empService.findById(id);
        model.addAttribute("emp", employee);

        List<Department> departments = departmentService.queryAll();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {

        logger.info("updateEmployee employee:{}", employee);
        Integer result = empService.updateById(employee);
        logger.info("updateEmployee employee:{}, result:{}", employee, result);

        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        empService.deleteById(id);
        return "redirect:/emps";
    }

}