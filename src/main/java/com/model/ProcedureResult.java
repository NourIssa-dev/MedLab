package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "TestRequest" database table.
 * 
 */
@Entity
@Table(name="\"ProcedureResult\"")
@NamedQuery(name="ProcedureResult.findAll", query="SELECT t FROM ProcedureResult t")
public class ProcedureResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="\"procedureResultID\"")
	private Integer procedureResultID;

	@Column(name="\"Result\"")
	private int result;
	
	@ManyToOne
	private Procedure procedure;
	
	@ManyToOne
	private RequestDetail requestDetail;
	


	public ProcedureResult() {
	}



	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}



	public Integer getProcedureResultID() {
		return procedureResultID;
	}



	public void setProcedureResultID(Integer procedureResultID) {
		this.procedureResultID = procedureResultID;
	}



	public Procedure getProcedure() {
		return procedure;
	}



	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}



	public RequestDetail getRequestDetail() {
		return requestDetail;
	}



	public void setRequestDetail(RequestDetail requestDetail) {
		this.requestDetail = requestDetail;
	}



}