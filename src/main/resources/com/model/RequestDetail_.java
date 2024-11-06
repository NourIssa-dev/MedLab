package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-12-25T15:39:00.162+0200")
@StaticMetamodel(RequestDetail.class)
public class RequestDetail_ {
	public static volatile SingularAttribute<RequestDetail, Integer> requestDetailID;
	public static volatile SingularAttribute<RequestDetail, String> notes;
	public static volatile SingularAttribute<RequestDetail, Result> result;
	public static volatile SingularAttribute<RequestDetail, Test> test;
}
