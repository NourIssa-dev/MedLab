package com.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.facade.DoctorFacade;
import com.facade.RequestFacade;
import com.model.Doctor;
import com.model.Guarantor;
import com.model.Patient;
import com.model.Request;
import com.model.RequestDetail;
import com.model.RequestPayment;



@Named
@ViewScoped
public class CashBoxReportController extends AbstractController<Request> implements Serializable {
	private Date fromDate;
	private Date toDate;
	private Doctor doctor;
	private String kind;
	private Date date;
	
	
	private List<Doctor> doctorList;
	private List<RequestPayment> payments=new ArrayList<RequestPayment>();
	
	
	@EJB
	 private RequestFacade requestFacade;
	
	@EJB
	 private DoctorFacade doctorFacade;
	
	
	
	@PostConstruct
	public void init() {
		doctorList=doctorFacade.findAll();
	}
	
	public String getMonth(Date date) {
		if(date !=null) {
		SimpleDateFormat sp=new SimpleDateFormat("EEE");
		return sp.format(date);
		}
		return "";
	}
	
	
	public void showResults() {
		payments= requestFacade.getPaymentsByConditions(fromDate,toDate,PayType.valueOf(kind),doctor);
		
	}
	
	public void showResults2() {
		payments= requestFacade.getPaymentsByConditions2(date,PayType.GUAR);
		
	}

	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public List<Doctor> getDoctorList() {
		return doctorList;
	}


	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}


	public List<RequestPayment> getPayments() {
		return payments;
	}


	public void setPayments(List<RequestPayment> payments) {
		this.payments = payments;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
}
