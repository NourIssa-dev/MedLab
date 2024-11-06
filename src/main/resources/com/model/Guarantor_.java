package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-12-25T15:39:00.155+0200")
@StaticMetamodel(Guarantor.class)
public class Guarantor_ {
	public static volatile SingularAttribute<Guarantor, Integer> guarID;
	public static volatile SingularAttribute<Guarantor, String> address;
	public static volatile SingularAttribute<Guarantor, String> guarName;
	public static volatile SingularAttribute<Guarantor, String> sponser;
	public static volatile ListAttribute<Guarantor, Request> requests;
	public static volatile SingularAttribute<Guarantor, ContactDetails> contactDetails;
}
