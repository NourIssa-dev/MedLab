package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.facade.AbstractFacade;
import com.facade.ClerkFacade;
import com.github.adminfaces.template.exception.BusinessException;

import com.model.Clerk;

@Named
@ViewScoped
public class ClerkLT extends AbstractController<Clerk> implements Serializable {

    @EJB
    ClerkFacade clerkFacade;

   List<Clerk> clerks=new ArrayList<Clerk>();
   List<Clerk> selectionList=new ArrayList<Clerk>();
   List<Clerk> filteredValue;
	@Override
	public void init() {
		clerks=clerkFacade.findAll();
		
	}
  
	 public String redirectToNextPage(Clerk c) {
	        // Perform some logic...

	        // Set a parameter in the Flash scope
	        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	        flash.put("clerkID",c.getClerkID());

	        // Add a faces message (optional)
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Redirected", null));

	        // Perform the redirect
	        return "request-form.xhtml?faces-redirect=true";
	    }
    public void delete() {
        int numCars = 0;
        for (Clerk selectedClerk : selectionList) {
            numCars++;
            clerkFacade.remove(selectedClerk);
        }
        selectionList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    }


	public List<Clerk> getClerks() {
		return clerks;
	}


	public void setClerks(List<Clerk> clerks) {
		this.clerks = clerks;
	}


	public List<Clerk> getSelectionList() {
		return selectionList;
	}


	public void setSelectionList(List<Clerk> selectionList) {
		this.selectionList = selectionList;
	}


	public List<Clerk> getFilteredValue() {
		return filteredValue;
	}


	public void setFilteredValue(List<Clerk> filteredValue) {
		this.filteredValue = filteredValue;
	}

    

}
