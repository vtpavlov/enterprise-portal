package com.vtpavlov.enterpriseportal.model.AccountsManagement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.vtpavlov.enterpriseportal.Repositories.IRepository;
import com.vtpavlov.enterpriseportal.dto.CompanyRole;
import com.vtpavlov.enterpriseportal.dto.User;

public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private int role;
	private IRepository<User> usersRepository;
	private IRepository<CompanyRole> rolesRepository;

	public RegisterBean() {
	}

	public RegisterBean(IRepository<User> usersRepository,
			IRepository<CompanyRole> rolesRepository) {
		this.usersRepository = usersRepository;
		this.rolesRepository = rolesRepository;
	}

	public Set<CompanyRole> getSelectedCompanyRole() {
		Set<CompanyRole> roles = new HashSet<CompanyRole>();
		switch (this.role) {
		case 1:
			roles.add(rolesRepository.get("JUNIOR"));
			break;
		case 2:
			roles.add(rolesRepository.get("SENIOR"));
			break;
		case 3:
			roles.add(rolesRepository.get("TEAMLEADER"));
			break;
		case 4:
			roles.add(rolesRepository.get("PROJECTLEADER"));
			break;
		case 5:
			roles.add(rolesRepository.get("PROJECTMANAGER"));
			break;
		default:			
			break;
		}
		
		return roles;		
	}
	
	public String register() {
		User user = new User(username, password, email, firstName, lastName,
				getSelectedCompanyRole());		
		
		usersRepository.add(user);

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {
			request.login(this.username, this.password);
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage("Login failed."));
			return "error";
		}

		return "welcome";
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public IRepository<User> getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(IRepository<User> usersRepository) {
		this.usersRepository = usersRepository;
	}

	public IRepository<CompanyRole> getRolesRepository() {
		return rolesRepository;
	}

	public void setRolesRepository(IRepository<CompanyRole> rolesRepository) {
		this.rolesRepository = rolesRepository;
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
