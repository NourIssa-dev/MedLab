package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-04-17T20:31:05.498+0300")
@StaticMetamodel(ProcedureResult.class)
public class ProcedureResult_ {
	public static volatile SingularAttribute<ProcedureResult, Integer> procedureResultID;
	public static volatile SingularAttribute<ProcedureResult, Integer> result;
	public static volatile SingularAttribute<ProcedureResult, Procedure> procedure;
	public static volatile SingularAttribute<ProcedureResult, RequestDetail> requestDetail;
}
