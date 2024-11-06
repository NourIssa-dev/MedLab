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
import com.facade.CategoryFacade;
import com.facade.DoctorFacade;
import com.facade.GuarantorFacade;
import com.facade.PatientFacade;
import com.facade.RequestFacade;
import com.facade.TestFacade;
import com.model.Category;
import com.model.Doctor;
import com.model.Guarantor;
import com.model.Patient;
import com.model.Procedure;
import com.model.ProcedureResult;
import com.model.Request;
import com.model.RequestDetail;
import com.model.RequestPayment;
import com.model.Test;


@Named
@ViewScoped
public class RequestController extends AbstractController<Request> implements Serializable {
 private Integer id;
 private Integer patientId;
 List<Guarantor> guarList;
 List<Test> testList;
 List<Doctor> doctorList;
 private Test selectedTest;
 private Category selectedCategory;
 private String paymentType;
 private double percentage;
 List<Category> categoryList;


 
@EJB
 private RequestFacade requestFacade;
 
 
 @EJB
 private PatientFacade patientFacade;
 
 @EJB
 private TestFacade testFacade;

 
 @EJB
 private GuarantorFacade guarantorFacade;
 
 @EJB
 private DoctorFacade doctorFacade;
 
 @EJB
 private CategoryFacade categoryFacade;
private List<Test> filteredTestList;
 


	@PostConstruct
	public void init() {
		guarList=guarantorFacade.findAll();
		testList = testFacade.findAll();
		doctorList=doctorFacade.findAll();
		categoryList=categoryFacade.findAll();
		 FacesContext facesContext = FacesContext.getCurrentInstance();

	        // Get the ExternalContext
	        ExternalContext externalContext = facesContext.getExternalContext();

	        // Get the request parameters map
	        Map<String, String> requestParams = externalContext.getRequestParameterMap();

	        // Retrieve the parameters
	        if(requestParams.get("id")!=null)
	        id =Integer.parseInt(requestParams.get("id"));
	        //new request
		if (id == null || id == 0) {
			entityClass = new Request();
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			if (flash != null && flash.get("patientID") != null) {
				patientId = (int) flash.get("patientID");
				if (patientId != null && patientId != 0) {
					Patient p = patientFacade.find(patientId);
					entityClass.setPatient(p);
					entityClass.setDetails(new ArrayList<RequestDetail>());
					entityClass.setPayments(new ArrayList<RequestPayment>());
					RequestPayment guarPayment = new RequestPayment();
					guarPayment.setPayType(PayType.GUAR);
					guarPayment.setRequest(entityClass);
					entityClass.getPayments().add(guarPayment);
					RequestPayment cashPayment = new RequestPayment();
					cashPayment.setPayType(PayType.CASH);
					cashPayment.setRequest(entityClass);
					entityClass.getPayments().add(cashPayment);
					entityClass.setReqDate(new Date());
				}
			} else {
				Faces.redirect("patients-list.xhtml", null);
			}
		}
		else {
			entityClass= requestFacade.find(id);
			percentage=	entityClass.getPayments().stream().filter(p->p.getPayType().equals(PayType.GUAR)).findAny().orElse(null).getPercentage();

		}
		
	}
	
	public void filterTests() {
		if(selectedCategory!=null) {
			setFilteredTestList(testList.stream().filter(t->t.getCategory().getId()==selectedCategory.getId()).collect(Collectors.toList()));
		}
	}
	
	 public void remove() {
	    	try {
	    		requestFacade.remove(entityClass);
	    		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "deleted successfully!", null);
	 	        FacesContext.getCurrentInstance().addMessage(null, message);
	 	        
	 	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	 	        try {
	 	            externalContext.redirect("requests-list.xhtml");
	 	        } catch (IOException e) {
	 	            e.printStackTrace(); 
	 	            }
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	
			
		}

	public Request save() {
		CounterManager.getInstance().incrementCounter();
		entityClass= requestFacade.save(entityClass);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "saved successfully!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	            externalContext.redirect("requests-list.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace(); 
	            }
		return entityClass;
	}
	
	
	
	
	public boolean isNew() {
		if(id==null || id==0 ) 
			return true;
		return false;
	}
	
	public void addNewRequestDetail() {
		if(selectedTest!=null) {
			RequestDetail requestDetail=new RequestDetail();
			requestDetail.setProcedureResults(new ArrayList<ProcedureResult>());
			requestDetail.setTest(selectedTest);
			requestDetail.setRequest(entityClass);
			entityClass.getDetails().add(requestDetail);
			entityClass.setTotalPrice(entityClass.getTotalPrice()+selectedTest.getPrice());
			for(Procedure p : selectedTest.getProcedures()) {
				ProcedureResult procedureResult=new ProcedureResult();
				procedureResult.setProcedure(p);
				procedureResult.setRequestDetail(requestDetail);
				requestDetail.getProcedureResults().add(procedureResult);
	
			}
		}
	}
	
	public void addNewPayment() {
		if(paymentType!=null) {
			RequestPayment requestPayment=new RequestPayment();
			requestPayment.setPayType(PayType.valueOf(paymentType));
			requestPayment.setPercentage(percentage);
			entityClass.getPayments().add(requestPayment);
		}
	}

	 
	 public List<Test> getTestList() {
		return testList;
	}

	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public List<Guarantor> getGuarList() {
		return guarList;
	}

	public void setGuarList(List<Guarantor> guarList) {
		this.guarList = guarList;
	}

	public Test getSelectedTest() {
		return selectedTest;
	}

	public void setSelectedTest(Test selectedTest) {
		this.selectedTest = selectedTest;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
		entityClass.getPayments().stream().filter(p->p.getPayType().equals(PayType.GUAR)).findAny().orElse(null).setPercentage(percentage);
	}

	public List<Doctor> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<Test> getFilteredTestList() {
		return filteredTestList;
	}

	public void setFilteredTestList(List<Test> filteredTestList) {
		this.filteredTestList = filteredTestList;
	}

//	@Override
//	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ejbRemove() throws EJBException, RemoteException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ejbActivate() throws EJBException, RemoteException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ejbPassivate() throws EJBException, RemoteException {
//		// TODO Auto-generated method stub
//
//	}

}
