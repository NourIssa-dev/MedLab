package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.facade.AbstractFacade;
import com.facade.TestFacade;
import com.github.adminfaces.template.exception.BusinessException;

import com.model.Test;

@Named
@ViewScoped
public class TestLT extends AbstractController<Test> implements Serializable {

    @EJB
    TestFacade testFacade;

   List<Test> tests=new ArrayList<Test>();
   List<Test> selectionList=new ArrayList<Test>();
   List<Test> filteredValue;
	@Override
	public void init() {
		tests=testFacade.findAll();
		
	}
  

    public void delete() {
        int numCars = 0;
        for (Test selectedTest : selectionList) {
            numCars++;
            testFacade.remove(selectedTest);
        }
        selectionList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    }


	public List<Test> getTests() {
		return tests;
	}


	public void setTests(List<Test> tests) {
		this.tests = tests;
	}


	public List<Test> getSelectionList() {
		return selectionList;
	}


	public void setSelectionList(List<Test> selectionList) {
		this.selectionList = selectionList;
	}


	public List<Test> getFilteredValue() {
		return filteredValue;
	}


	public void setFilteredValue(List<Test> filteredValue) {
		this.filteredValue = filteredValue;
	}

    

}
