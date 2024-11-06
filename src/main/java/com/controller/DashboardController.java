package com.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;

import com.facade.RequestFacade;
import com.facade.TestFacade;
import com.model.ProcedureResult;
import com.model.Request;
import com.model.RequestDetail;
import com.model.Test;

@Named
@ViewScoped
public class DashboardController extends AbstractController<Request> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RequestFacade requestFacade;
    
    @Inject
    private TestFacade testFacade;


    private List<Test> testsList = new ArrayList<>();
    private Integer selectedRequestId;
    private Date fromDate;
    private Date toDate;
    private BarChartModel barModel=new BarChartModel();

	@PostConstruct
    public void init() {
        testsList = testFacade.findAll();
       
        if (testsList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No requests found"));
        }
    }

    public void generateChart() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet dataSet = new BarChartDataSet();

        dataSet.setLabel("Test Results Over Time");
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        try {
   //         List<RequestDetail> requestDetails = requestFacade.getRequestDetailsByRequestIdAndDateRange(selectedRequestId, fromDate, toDate);
//            for (RequestDetail detail : requestDetails) {
//                for (ProcedureResult result : detail.getProcedureResults()) {
//                    values.add(result.getResult());
//                    labels.add(new SimpleDateFormat("MM/yyyy").format(detail.getRequest().getReqDate()));
//                }
//            }

            dataSet.setData(values);
            data.addChartDataSet(dataSet);
            data.setLabels(labels);

            barModel.setData(data);

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Test Results Over Time (Request ID: " + selectedRequestId + ")");
            barModel.getOptions().setTitle(title);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public Integer getSelectedRequestId() {
        return selectedRequestId;
    }

    public void setSelectedRequestId(Integer selectedRequestId) {
        this.selectedRequestId = selectedRequestId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

	public TestFacade getTestFacade() {
		return testFacade;
	}

	public void setTestFacade(TestFacade testFacade) {
		this.testFacade = testFacade;
	}

	public List<Test> getTestsList() {
		return testsList;
	}

	public void setTestsList(List<Test> testsList) {
		this.testsList = testsList;
	}
}