package com.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.facade.RequestFacade;
import com.model.NormalValue;
import com.model.ProcedureResult;
import com.model.Request;
import com.model.RequestDetail;
import com.model.Test;

@Named
@ViewScoped
public class PatientResultsController extends AbstractController<Request> implements Serializable {

	private Integer selectedRequestId;
	private List<Integer> RequestsIDs = new ArrayList<Integer>();
	private Integer id;
	private LineChartModel barModel=new LineChartModel();

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
	
	public void createGraph(Test test) {
		 barModel = new LineChartModel();
	        ChartData data = new ChartData();
	      

	       
	        List<Number> values = new ArrayList<>();
	        List<String> labels = new ArrayList<>();

	        LineChartDataSet  dataSet = new LineChartDataSet();
	            List<RequestDetail> requestDetails = requestFacade.findByTest(test);
	            for (RequestDetail detail : requestDetails) {
	            	
	                for (ProcedureResult result : detail.getProcedureResults()) {
	                    values.add(result.getResult());
	                    labels.add(new SimpleDateFormat("dd/MM/yyyy").format(detail.getRequest().getReqDate()));
	                }
	               
	            }

	            dataSet.setData(values);
	            data.addChartDataSet(dataSet);
	            data.setLabels(labels);
barModel.setData(data);

	            Title title = new Title();
	            title.setDisplay(true);
	            title.setText("Test Results Over Time (Request ID: " + id + ")");
	            
	            LineChartOptions options = new LineChartOptions();
	           
	            options.setTitle(title);
	            PrimeFaces.current().executeScript("PF('testDialog').show()");
	           
	}

	public void onSelect() {
		entityClass= requestFacade.find(selectedRequestId);
	}

	public String checkInRange(ProcedureResult procedureResult) {

		List<NormalValue> normalValues = procedureResult.getProcedure().getNormalValues();
		String gender = procedureResult.getRequestDetail().getRequest().getPatient().getGender();
		String stage = procedureResult.getRequestDetail().getRequest().getPatient().getAge() <= 18 ? "CHILD" : "ADULT";
		normalValues = normalValues.stream()
				.filter(p -> p.getGender().equals(gender.toUpperCase()) && p.getAgeStage().equals(stage))
				.collect(Collectors.toList());
		if (normalValues.stream().filter(
				p -> procedureResult.getResult() < p.getFromRange() || procedureResult.getResult() > p.getToRange())
				.count() > 0) {
			return "bg-red";

		}
		return "";

	}

	public List<ProcedureResult> requestProceduresResults2() {
		List<ProcedureResult> results = new ArrayList<>();
		if (entityClass != null)
			results = (entityClass.getDetails().stream().flatMap(p -> p.getProcedureResults().stream())
					.collect(Collectors.toList()));
		return results;
	}

	public List<Integer> getRequestsIDs() {
		return RequestsIDs;
	}

	public void setRequestsIDs(List<Integer> requestsIDs) {
		RequestsIDs = requestsIDs;
	}

	public RequestFacade getRequestFacade() {
		return requestFacade;
	}

	public void setRequestFacade(RequestFacade requestFacade) {
		this.requestFacade = requestFacade;
	}

	public Integer getSelectedRequestId() {
		return selectedRequestId;
	}

	public void setSelectedRequestId(Integer selectedRequestId) {
		this.selectedRequestId = selectedRequestId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LineChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(LineChartModel barModel) {
		this.barModel = barModel;
	}

}
