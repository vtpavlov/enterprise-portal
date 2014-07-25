package com.vtpavlov.enterpriseportal.model;

import java.util.HashSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vtpavlov.enterpriseportal.Repositories.IRepository;
import com.vtpavlov.enterpriseportal.dto.CompanyRole;
import com.vtpavlov.enterpriseportal.dto.Page;
import com.vtpavlov.enterpriseportal.dto.User;


@ManagedBean
@SessionScoped
public class UserBean {
	private IRepository<User> userRepository;
	private IRepository<CompanyRole> companyRoleRepository;
	private IRepository<Page> pageRepository;
	
	public UserBean () {}
	
	public UserBean(IRepository<User> emp, IRepository<CompanyRole> cr,
			IRepository<Page> page) {
		this.userRepository = emp;
		this.companyRoleRepository = cr;
		this.pageRepository = page;
	}
	public String save() {
		CompanyRole role = companyRoleRepository.add(new CompanyRole("JUNIOR"));
		userRepository.add(new User("Pesho","23rf3","d2f3",role));
		Page pageItem = new Page("C:/asdad/asd","Hibernate", "ORM technology");
		HashSet<CompanyRole> roles = new HashSet<CompanyRole>();
		roles.add(role);
		pageItem.setAllowedRoles(roles);
		pageRepository.add(pageItem);
		return "index";
	}

	public IRepository<User> getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(IRepository<User> userRepository) {
		this.userRepository = userRepository;
	}

	public IRepository<CompanyRole> getCompanyRoleRepository() {
		return companyRoleRepository;
	}

	public void setCompanyRoleRepository(
			IRepository<CompanyRole> companyRoleRepository) {
		this.companyRoleRepository = companyRoleRepository;
	}

	public IRepository<Page> getPageRepository() {
		return pageRepository;
	}

	public void setPageRepository(IRepository<Page> pageRepository) {
		this.pageRepository = pageRepository;
	}
}
