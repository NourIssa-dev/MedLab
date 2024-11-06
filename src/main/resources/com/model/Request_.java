package com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-12-25T15:39:00.159+0200")
@StaticMetamodel(Request.class)
public class Request_ {
	public static volatile SingularAttribute<Request, Integer> requestID;
	public static volatile SingularAttribute<Request, Boolean> delivered;
	public static volatile SingularAttribute<Request, Double> remaining;
	public static volatile SingularAttribute<Request, Date> reqDate;
	public static volatile SingularAttribute<Request, Double> total;
	public static volatile ListAttribute<Request, RequestPayment> payments;
	public static volatile SingularAttribute<Request, Doctor> doctor;
	public static volatile SingularAttribute<Request, Guarantor> guarantor;
	public static volatile SingularAttribute<Request, Patient> patient;
	public static volatile ListAttribute<Request, RequestDetail> details;
	public static volatile SingularAttribute<Request, Integer> version;
}
