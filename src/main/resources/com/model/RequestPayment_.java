package com.model;

import com.enums.PayType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-12-25T15:39:00.171+0200")
@StaticMetamodel(RequestPayment.class)
public class RequestPayment_ {
	public static volatile SingularAttribute<RequestPayment, Integer> payID;
	public static volatile SingularAttribute<RequestPayment, Double> amount;
	public static volatile SingularAttribute<RequestPayment, Date> payDate;
	public static volatile SingularAttribute<RequestPayment, PayType> payType;
	public static volatile SingularAttribute<RequestPayment, Double> percentage;
	public static volatile SingularAttribute<RequestPayment, Request> request;
}
