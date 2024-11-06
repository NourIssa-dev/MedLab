package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-05-26T12:53:53.365+0300")
@StaticMetamodel(Procedure.class)
public class Procedure_ {
	public static volatile SingularAttribute<Procedure, Integer> id;
	public static volatile SingularAttribute<Procedure, String> name;
	public static volatile SingularAttribute<Procedure, Test> test;
	public static volatile ListAttribute<Procedure, NormalValue> normalValues;
}
