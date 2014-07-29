package com.vtpavlov.enterpriseportal.model.AccountsManagement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class GoogleLoginBean {
	@ManagedProperty("#{param.code}")
	private String code;

	public String login() {
		//TODO Should send the code to google API server and receive a token
		System.out.print(code);
		return "login";
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
