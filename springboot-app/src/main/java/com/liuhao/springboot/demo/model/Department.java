package com.liuhao.springboot.demo.model;

import com.liuhao.springboot.demo.dto.BaseObject;

public class Department extends BaseObject {

	private static final long serialVersionUID = -4820828443780893895L;
	private Integer id;
	private String departmentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
