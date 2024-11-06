package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-04-16T23:11:07.812+0300")
@StaticMetamodel(NormalValue.class)
public class NormalValue_ {
	public static volatile SingularAttribute<NormalValue, Integer> id;
	public static volatile SingularAttribute<NormalValue, String> gender;
	public static volatile SingularAttribute<NormalValue, String> ageStage;
	public static volatile SingularAttribute<NormalValue, Double> fromRange;
	public static volatile SingularAttribute<NormalValue, Double> toRange;
	public static volatile SingularAttribute<NormalValue, Procedure> procedure;
}
