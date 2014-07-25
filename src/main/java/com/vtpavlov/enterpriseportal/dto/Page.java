package com.vtpavlov.enterpriseportal.dto;

import java.util.Set;

public class Page {
	private int id;
	private String filePath;
	private String title;
	private String description;
	private Set<CompanyRole> allowedRoles;
	
	public Page(String filePath, String title, String description) {
		super();
		this.filePath = filePath;
		this.title = title;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<CompanyRole> getAllowedRoles() {
		return allowedRoles;
	}
	public void setAllowedRoles(Set<CompanyRole> allowedRoles) {
		this.allowedRoles = allowedRoles;
	}
}
