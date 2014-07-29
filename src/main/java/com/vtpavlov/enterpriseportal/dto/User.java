package com.vtpavlov.enterpriseportal.dto;

import java.util.Set;

public class User {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	private Set<CompanyRole> roles;

	public User() {
	}

	public User(String username, String password, String email,
			String firstName, String lastName, Set<CompanyRole> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<CompanyRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<CompanyRole> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
