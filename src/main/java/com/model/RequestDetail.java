package com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the "TestRequest" database table.
 * 
 */
@Entity
@Table(name="\"RequestDetail\"")
@NamedQuery(name="RequestDetail.FindByTest", query="SELECT t FROM RequestDetail t where t.test.testCode=:test order by t.request.reqDate")
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FindByTest = "RequestDetail.FindByTest";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="\"RequestDetailID\"")
	private Integer requestDetailID;

	@Column(name="\"Notes\"")
	private String notes;

	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "requestDetail")
	private List<ProcedureResult> procedureResults;
	
	@ManyToOne
	private Test test;
	
	@ManyToOne
	private Request request;
	


	public RequestDetail() {
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Integer getRequestDetailID() {
		return requestDetailID;
	}

	public void setRequestDetailID(Integer requestDetailID) {
		this.requestDetailID = requestDetailID;
	}

	public List<ProcedureResult> getProcedureResults() {
		return procedureResults;
	}

	public void setProcedureResults(List<ProcedureResult> procedureResults) {
		this.procedureResults = procedureResults;
	}



}