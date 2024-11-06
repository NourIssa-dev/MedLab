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
import com.facade.DoctorFacade;
import com.github.adminfaces.template.exception.BusinessException;
import com.model.Doctor;


@Named
@ViewScoped
public class DoctorLT extends AbstractController<Doctor> implements Serializable {

    @EJB
    DoctorFacade doctorFacade;

   List<Doctor> doctors=new ArrayList<Doctor>();
   List<Doctor> selectionList=new ArrayList<Doctor>();
   List<Doctor> filteredValue;
	@Override
	public void init() {
		doctors=doctorFacade.findAll();
		
	}
  

    public void delete() {
        int numCars = 0;
        for (Doctor selectedDoctor : selectionList) {
            numCars++;
            doctorFacade.remove(selectedDoctor);
        }
        selectionList.clear();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    }


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}


	public List<Doctor> getSelectionList() {
		return selectionList;
	}


	public void setSelectionList(List<Doctor> selectionList) {
		this.selectionList = selectionList;
	}


	public List<Doctor> getFilteredValue() {
		return filteredValue;
	}


	public void setFilteredValue(List<Doctor> filteredValue) {
		this.filteredValue = filteredValue;
	}

    

}
