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
import com.facade.RequestFacade;
import com.github.adminfaces.template.exception.BusinessException;

import com.model.Request;

@Named
@ViewScoped
public class RequestLT extends AbstractController<Request> implements Serializable {

    @EJB
    RequestFacade requestFacade;

   List<Request> requests=new ArrayList<Request>();
   List<Request> selectionList=new ArrayList<Request>();
   List<Request> filteredValue;
	@Override
	public void init() {
		requests=requestFacade.findAll();
		
	}
  

    public void delete() {
        int numCars = 0;
        for (Request selectedRequest : selectionList) {
            numCars++;
            requestFacade.remove(selectedRequest);
        }
        selectionList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    }


	public List<Request> getRequests() {
		return requests;
	}


	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}


	public List<Request> getSelectionList() {
		return selectionList;
	}


	public void setSelectionList(List<Request> selectionList) {
		this.selectionList = selectionList;
	}


	public List<Request> getFilteredValue() {
		return filteredValue;
	}


	public void setFilteredValue(List<Request> filteredValue) {
		this.filteredValue = filteredValue;
	}

    

}
