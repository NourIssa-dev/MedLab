package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-04-10T22:46:51.158+0300")
@StaticMetamodel(Test.class)
public class Test_ {
	public static volatile SingularAttribute<Test, Integer> id;
	public static volatile SingularAttribute<Test, String> testCode;
	public static volatile SingularAttribute<Test, Double> price;
	public static volatile SingularAttribute<Test, String> testName;
	public static volatile SingularAttribute<Test, Category> category;
	public static volatile ListAttribute<Test, Procedure> procedures;
}
