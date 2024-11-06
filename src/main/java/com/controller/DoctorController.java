package com.controller;
import com.facade.DoctorFacade;


import com.model.Doctor;

import org.omnifaces.util.Faces;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;


@Named
@ViewScoped
public class DoctorController extends AbstractController<Doctor> implements Serializable {
 private Integer id;
 
 @EJB
 private DoctorFacade doctorFacade;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(id==null || id==0 ) {
			entityClass=new Doctor();
		}
		else {
			entityClass= doctorFacade.find(id);
		}
		
	}
	public Doctor save() {
		Doctor p= doctorFacade.save(entityClass);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            externalContext.redirect("doctors-list.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            }
		return p;
	}
	public boolean isNew() {
		if(id==null || id==0 ) 
			return true;
		return false;
	}


	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


}
