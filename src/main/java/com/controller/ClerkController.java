package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.facade.ClerkFacade;
import com.model.Clerk;

@Named
@ViewScoped
public class ClerkController extends AbstractController<Clerk> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;


	@EJB
	private ClerkFacade clerkFacade;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			entityClass = new Clerk();
		} else {
			entityClass = clerkFacade.find(id);
		}

	}


	public Clerk save() {
		entityClass = clerkFacade.save(entityClass);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("clerks_list.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityClass;
	}

	public boolean isNew() {
		if (id == null || id == 0)
			return true;
		return false;
	}

	public void handleAddRequest(String clerkID) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().put("clerkID", clerkID);

		try {
			facesContext.getExternalContext().redirect("request-form.xhtml?faces-redirect=true");
		} catch (IOException e) {
			// Handle exception if necessary
		}
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
