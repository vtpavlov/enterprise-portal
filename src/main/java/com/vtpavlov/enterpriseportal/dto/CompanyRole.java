package com.vtpavlov.enterpriseportal.dto;

public class CompanyRole {
	private int id;
	private String companyRole;
	
	public CompanyRole() {}
	
	public CompanyRole(String companyRole) {

		this.companyRole = companyRole;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyRole() {
		return companyRole;
	}
	public void setCompanyRole(String companyRole) {
		this.companyRole = companyRole;
	}
}
