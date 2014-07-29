package com.vtpavlov.enterpriseportal.model.Pages;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.vtpavlov.enterpriseportal.Repositories.IRepository;
import com.vtpavlov.enterpriseportal.dto.Page;
import com.vtpavlov.enterpriseportal.dto.User;
import com.vtpavlov.enterpriseportal.dto.CompanyRole;

public class CreatingPageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String text;
	private String pageTitle;
	private String pageDescription;
	private IRepository<User> userRepository;
	private IRepository<Page> pageRepository;
	private IRepository<CompanyRole> roleRepository;
	
	public CreatingPageBean() {
	}

	public CreatingPageBean(IRepository<User> userRepository,
			IRepository<Page> pageRepository,
			IRepository<CompanyRole> roleRepository) {
		this.userRepository = userRepository;
		this.pageRepository = pageRepository;
		this.roleRepository = roleRepository;
	}

	public String savePage() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		ServletContext sc = (ServletContext)externalContext.getContext();
		
		String username = externalContext.getRemoteUser();
		User currentUser = userRepository.get(username);
	
		String webInfPath = sc.getRealPath("/WEB-INF/");
		
		String filePath = webInfPath + "/userpages/" + pageTitle
				+ ".html";
		
		Page currentlyCreatedPage = new Page(filePath, pageTitle, pageDescription);
		
		currentlyCreatedPage.setOwner(currentUser);
		
		Set<CompanyRole> allowedRoles = new HashSet<CompanyRole>();
		allowedRoles.add(roleRepository.get("JUNIOR"));
		
		currentlyCreatedPage.setAllowedRoles(allowedRoles);
		
		try (PrintStream pageWriter = new PrintStream(filePath)) {			
			pageWriter.print(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageRepository.add(currentlyCreatedPage);
		
		return "showpage";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public IRepository<User> getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(IRepository<User> userRepository) {
		this.userRepository = userRepository;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public IRepository<Page> getPageRepository() {
		return pageRepository;
	}

	public void setPageRepository(IRepository<Page> pageRepository) {
		this.pageRepository = pageRepository;
	}

	public IRepository<CompanyRole> getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(IRepository<CompanyRole> roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	
}
