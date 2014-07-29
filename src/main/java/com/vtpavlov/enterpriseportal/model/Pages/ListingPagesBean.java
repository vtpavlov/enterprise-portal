package com.vtpavlov.enterpriseportal.model.Pages;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import com.vtpavlov.enterpriseportal.Repositories.IRepository;
import com.vtpavlov.enterpriseportal.dto.Page;

public class ListingPagesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Page> pages;
	private IRepository<Page> pageRepository;

	public ListingPagesBean() {}
	
	public ListingPagesBean(IRepository<Page> pageRepository) {
		this.pageRepository = pageRepository;
		//initPages();
	}
	
	@PostConstruct
	public void initPages() {
		pages = pageRepository.getAll();
	}

	public List<Page> getPages() {		
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public IRepository<Page> getPageRepository() {
		return pageRepository;
	}

	public void setPageRepository(IRepository<Page> pageRepository) {
		this.pageRepository = pageRepository;
	}
}
