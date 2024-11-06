package com.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the "Request" database table.
 * 
 */
@Entity
@Table(name = "\"Request\"")
@NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "\"RequestID\"")
	private Integer requestID;

	@Column(name = "\"Delivered\"")
	private Boolean delivered;

	@Temporal(TemporalType.DATE)
	@Column(name = "\"ReqDate\"")
	private Date reqDate;

	@Column(name = "\"Total Price\"")
	private double totalPrice;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "request")
	private List<RequestPayment> payments;


	@ManyToOne
	private Doctor doctor;


	@ManyToOne
	private Guarantor guarantor;

	@ManyToOne
	private Patient patient;
	
	@Version
	private int version;
	
	//added
	@Fetch(FetchMode.JOIN)
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "request")
	private List<RequestDetail> details;

	public Integer getRequestID() {
		return this.requestID;
	}

	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}

	public Boolean getDelivered() {
		return this.delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public Date getReqDate() {
		return this.reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<RequestPayment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<RequestPayment> payments) {
		this.payments = payments;
	}

	public RequestPayment addPayment(RequestPayment payment) {
		getPayments().add(payment);

		return payment;
	}

	public RequestPayment removePayment(RequestPayment payment) {
		getPayments().remove(payment);
		return payment;
	}

	

	public Guarantor getGuarantor() {
		return this.guarantor;
	}

	public void setGuarantor(Guarantor guarantor) {
		this.guarantor = guarantor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	 public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	

	@PrePersist
	    public void prePersist() {
	        System.out.println("Request with ID " + requestID + " is being persisted.");
	    }

	    @PreRemove
	    public void preRemove() {
	        System.out.println("Request with ID " + requestID + " is being removed.");
	    }

		public List<RequestDetail> getDetails() {
			return details;
		}

		public void setDetails(List<RequestDetail> details) {
			this.details = details;
		}
		@Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;

	        Request req = (Request) obj;
	        return Objects.equals(requestID, req.requestID);
	    } 

	    @Override
	    public int hashCode() {
	        return Objects.hash(requestID);
	    }
	    

}