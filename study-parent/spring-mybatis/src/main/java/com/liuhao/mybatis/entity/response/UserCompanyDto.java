package com.liuhao.mybatis.entity.response;

public class UserCompanyDto {

	/**
	 * 个人信息
	 */
    private int id;
    private String userName;
    private int userAge;
    private String userAddress;

    /**
     * 公司信息
     */
    private String companyName;
    private int companyPersons;
    private String companyAddress;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCompanyPersons() {
		return companyPersons;
	}
	public void setCompanyPersons(int companyPersons) {
		this.companyPersons = companyPersons;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	@Override
	public String toString() {
		return "UserComanyDto [id=" + id + ", userName=" + userName + ", userAge=" + userAge + ", userAddress="
				+ userAddress + ", companyName=" + companyName + ", companyPersons=" + companyPersons
				+ ", companyAddress=" + companyAddress + "]";
	}
}
