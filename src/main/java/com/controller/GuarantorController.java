/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.facade.GuarantorFacade;
import com.model.Guarantor;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;


@Named
@ViewScoped
public class GuarantorController extends AbstractController<Guarantor> implements Serializable {
 private Integer id;
 
 @EJB
 private GuarantorFacade gurFacade;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		if(id==null || id==0 ) {
			entityClass=new Guarantor();
		}
		else {
			entityClass= gurFacade.find(id);
		}
		
	}
	public Guarantor save() {
		Guarantor g= gurFacade.save(entityClass);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            externalContext.redirect("guarantors-list.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            }
		return g;
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
