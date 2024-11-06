package com.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import com.enums.PayType;
import com.facade.RequestFacade;
import com.model.NormalValue;
import com.model.Patient;
import com.model.Procedure;
import com.model.ProcedureResult;
import com.model.Request;
import com.model.RequestDetail;
import com.model.RequestPayment;
import com.model.Test;



@Named
@ViewScoped
public class ResultController extends AbstractController<Request> implements Serializable {
	private Integer id;
	
	
	@EJB
	 private RequestFacade requestFacade;
	
	
	
	@PostConstruct
	public void init() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();

	        // Get the ExternalContext
	        ExternalContext externalContext = facesContext.getExternalContext();

	        // Get the request parameters map
	        Map<String, String> requestParams = externalContext.getRequestParameterMap();

	        // Retrieve the parameters
	        id =Integer.parseInt(requestParams.get("id"));
		if (id == null || id == 0) {
			
		}
		else {
			entityClass= requestFacade.find(id);
			
	    }
	}
	
	public String checkInRange(ProcedureResult procedureResult) {
		
		List<NormalValue> normalValues=procedureResult.getProcedure().getNormalValues();
		String gender=procedureResult.getRequestDetail().getRequest().getPatient().getGender();
		String stage=procedureResult.getRequestDetail().getRequest().getPatient().getAge()<=18?"CHILD":"ADULT";
		normalValues=normalValues.stream().filter(p->p.getGender().equals(gender.toUpperCase()) && p.getAgeStage()
				.equals(stage)).collect(Collectors.toList());
		if(normalValues.stream().filter(p->procedureResult.getResult()<p.getFromRange() ||
				procedureResult.getResult()>p.getToRange()).count()>0) {
			return "bg-red";
			
		}
		return "";
		
	}
	
	public List<ProcedureResult> requestProceduresResults() {
		List<ProcedureResult> results = (entityClass.getDetails().stream().flatMap(p -> p.getProcedureResults().stream())
				.collect(Collectors.toList()));
		return results;
	
	}
	public Request save() {
		Request r= requestFacade.save(entityClass);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            externalContext.redirect("requests-list.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            }
		return r;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
