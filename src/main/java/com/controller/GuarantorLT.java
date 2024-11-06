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
import com.facade.GuarantorFacade;
import com.github.adminfaces.template.exception.BusinessException;
import com.model.Guarantor;

@Named
@ViewScoped
public class GuarantorLT extends AbstractController<Guarantor> implements Serializable {

    @EJB
    GuarantorFacade guarantorFacade;

   List<Guarantor> guarantors=new ArrayList<Guarantor>();
   List<Guarantor> selectionList=new ArrayList<Guarantor>();
   List<Guarantor> filteredValue;
	@Override
	public void init() {
		guarantors=guarantorFacade.findAll();
		
	}
  

    public void delete() {
        int numCars = 0;
        for (Guarantor selectedGuarantor : selectionList) {
            numCars++;
            guarantorFacade.remove(selectedGuarantor);
        }
        selectionList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    }


	public List<Guarantor> getGuarantors() {
		return guarantors;
	}


	public void setGuarantors(List<Guarantor> guarantors) {
		this.guarantors = guarantors;
	}


	public List<Guarantor> getSelectionList() {
		return selectionList;
	}


	public void setSelectionList(List<Guarantor> selectionList) {
		this.selectionList = selectionList;
	}


	public List<Guarantor> getFilteredValue() {
		return filteredValue;
	}


	public void setFilteredValue(List<Guarantor> filteredValue) {
		this.filteredValue = filteredValue;
	}

    

}
