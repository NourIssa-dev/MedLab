package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-04-16T22:09:43.091+0300")
@StaticMetamodel(RequestDetail.class)
public class RequestDetail_ {
	public static volatile SingularAttribute<RequestDetail, Integer> requestDetailID;
	public static volatile SingularAttribute<RequestDetail, String> notes;
	public static volatile ListAttribute<RequestDetail, ProcedureResult> procedureResults;
	public static volatile SingularAttribute<RequestDetail, Test> test;
	public static volatile SingularAttribute<RequestDetail, Request> request;
}
