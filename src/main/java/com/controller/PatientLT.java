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
import com.facade.PatientFacade;
import com.github.adminfaces.template.exception.BusinessException;

import com.model.Patient;

@Named
@ViewScoped
public class PatientLT extends AbstractController<Patient> implements Serializable {

	@EJB
	PatientFacade patientFacade;

	List<Patient> patients = new ArrayList<Patient>();
	List<Patient> selectionList = new ArrayList<Patient>();
	List<Patient> filteredValue;

	@Override
	public void init() {
		patients = patientFacade.findAll();

	}

	public String redirectToNextPage(Patient p) {
		// Set a parameter in the Flash scope
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("patientID", p.getPatientID());

		// Add a faces message (optional)
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Redirected", null));

		// Perform the redirect
		return "request-form.xhtml?faces-redirect=true";
	}

	public void delete() {
		int n = 0;
		for (Patient selectedPatient : selectionList) {
			n++;
			patientFacade.remove(selectedPatient);
		}
		selectionList.clear();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Patient> getSelectionList() {
		return selectionList;
	}

	public void setSelectionList(List<Patient> selectionList) {
		this.selectionList = selectionList;
	}

	public List<Patient> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Patient> filteredValue) {
		this.filteredValue = filteredValue;
	}

}
