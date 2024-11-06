package com.controller;
import com.facade.CategoryFacade;
import com.facade.TestFacade;

import com.model.Test;
import com.model.Category;
import com.model.NormalValue;
import com.model.Procedure;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


@Named
@ViewScoped
public class TestController extends AbstractController<Test> implements Serializable { 
 private Integer id;
 private List<Category> categoryList=new ArrayList<>();
 private Category category=new Category();
 private NormalValue normalValue=new NormalValue();
 private String procedureName;
 
 @EJB
 private TestFacade testFacade;
 @EJB
 private CategoryFacade categoryFacade;
private Procedure selectedProcedure;
	
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		 FacesContext facesContext = FacesContext.getCurrentInstance();

	        // Get the ExternalContext
	        ExternalContext externalContext = facesContext.getExternalContext();

	        // Get the request parameters map
	        Map<String, String> requestParams = externalContext.getRequestParameterMap();

	        // Retrieve the parameters
	        if(requestParams.get("id")!=null)
	        id =Integer.parseInt(requestParams.get("id"));
	        //new request
		if (id == null || id == 0) {
			entityClass=new Test();
		}
		else {
			entityClass= testFacade.find(id);
		}
		categoryList=categoryFacade.findAll();
		if(entityClass.getProcedures()==null)
			entityClass.setProcedures(new ArrayList<Procedure>());
		
	}
	
	public void addNewProcedure() {
		if(procedureName!=null && !procedureName.equals("")) {
			Procedure p=new Procedure(procedureName);
			p.setTest(entityClass);
			entityClass.addToProcedures(p);
		}
	}
	
	public void addCategory() {
		category=new Category();
	}
	
	public void saveCategory() {
		category=categoryFacade.save(category);
		categoryList=categoryFacade.findAll();
	}
	
	public void addNormalValue(Procedure p) {
		normalValue=new NormalValue();
		normalValue.setProcedure(p);
		setSelectedProcedure(p);
	}
	
	public void saveNormalValue() {
		selectedProcedure.getNormalValues().add(normalValue);
		entityClass.getProcedures().set(entityClass.getProcedures().indexOf(selectedProcedure), selectedProcedure);
	}
	public Test save() {
		Test t= testFacade.save(entityClass);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            externalContext.redirect("tests-list.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            }
		return t;
	}
	public boolean isNew() {
		if(id==null ) 
			return true;
		return false;
	}


	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}


	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public NormalValue getNormalValue() {
		return normalValue;
	}

	public void setNormalValue(NormalValue normalValue) {
		this.normalValue = normalValue;
	}

	public Procedure getSelectedProcedure() {
		return selectedProcedure;
	}

	public void setSelectedProcedure(Procedure selectedProcedure) {
		this.selectedProcedure = selectedProcedure;
	}


}
