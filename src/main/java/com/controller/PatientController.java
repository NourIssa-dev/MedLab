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

import com.facade.PatientFacade;
import com.model.Patient;

@Named
@ViewScoped
public class PatientController extends AbstractController<Patient> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private UploadedFile filee;

	@EJB
	private PatientFacade patientFacade;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			entityClass = new Patient();
		} else {
			entityClass = patientFacade.find(id);
		}

	}


	public Patient save() {
		entityClass = patientFacade.save(entityClass);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect("patients-list.xhtml");
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

	public void handleAddRequest(String patientID) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().put("patientID", patientID);

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

	public UploadedFile getFilee() {
		return filee;
	}

	public void setFilee(UploadedFile filee) {
		this.filee = filee;
	}

}
