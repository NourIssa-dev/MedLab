package com.model;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.PayType;

import java.util.Date;
import java.util.stream.Collectors;


/**
 * The persistent class for the "Payment" database table.
 * 
 */
@Entity
@Table(name="\"RequestPayment\"")
@NamedQueries({
@NamedQuery(name="RequestPayment.findAll", query="SELECT p FROM RequestPayment p"),
@NamedQuery(name="RequestPayment.findByConditions", query="SELECT p FROM RequestPayment p where p.request.reqDate between :fromDate and :toDate and p.payType=:kind and p.request.doctor.doctorID=:doctor and p.amount>0"),
@NamedQuery(name="RequestPayment.findByConditions2", query="SELECT p FROM RequestPayment p where EXTRACT(month from p.request.reqDate) = :month and EXTRACT(year from p.request.reqDate) = :year  and p.payType=:kind and p.amount>0" )

})
public class RequestPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FindByConditions = "RequestPayment.findByConditions";
	public static final String FindByConditions2 = "RequestPayment.findByConditions2";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="\"PayID\"")
	private Integer payID;

	@Column(name="\"Amount\"")
	private double amount;

	@Temporal(TemporalType.DATE)
	@Column(name="\"PayDate\"")
	private Date payDate;
	
	@Column(name="\"PayType\"")
	private PayType payType;

	@Column(name="\"Percentage\"")
	private double percentage;
	
	@ManyToOne
	private Request request;
	

	public RequestPayment() {
	}

	public Integer getPayID() {
		return this.payID;
	}

	public void setPayID(Integer payID) {
		this.payID = payID;
	}

	public double getAmount() {
	this.amount=request.getTotalPrice()*this.getPercentage()/100;
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public double getPercentage() {
		if (payType.equals(PayType.GUAR))
			return this.percentage;
		else {
			this.percentage = 100 - request.getPayments().stream().filter(p -> p.getPayType().equals(PayType.GUAR))
					.collect(Collectors.summarizingDouble(RequestPayment::getPercentage)).getSum();
			return percentage;
		}
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}


}