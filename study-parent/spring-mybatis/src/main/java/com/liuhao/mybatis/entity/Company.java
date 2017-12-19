package com.liuhao.mybatis.entity;

public class Company {
	
    private int id;
    private String name;
    private int persons;
    private String address;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPersons() {
		return persons;
	}
	
	public void setPersons(int persons) {
		this.persons = persons;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", persons=" + persons + ", address=" + address + "]";
	}
	
}
