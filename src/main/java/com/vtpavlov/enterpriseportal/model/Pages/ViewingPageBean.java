package com.vtpavlov.enterpriseportal.model.Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ViewingPageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String text;
	
	@ManagedProperty(value="#{param.filePath}")
	private String filePath;
	
	public ViewingPageBean() {
		
	}
	
	@PostConstruct
	public void readPage() {
		StringBuilder pageContent = new StringBuilder();
		File file = new File(filePath);
		try(Scanner fileReader = new Scanner(file)) {
			while(fileReader.hasNextLine()) {
				pageContent.append(fileReader.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.text = pageContent.toString();
	}
	
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
