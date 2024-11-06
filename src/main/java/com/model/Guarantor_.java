package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-01-24T19:35:57.473+0200")
@StaticMetamodel(Guarantor.class)
public class Guarantor_ {
	public static volatile SingularAttribute<Guarantor, Integer> guarID;
	public static volatile SingularAttribute<Guarantor, String> address;
	public static volatile SingularAttribute<Guarantor, String> guarName;
	public static volatile SingularAttribute<Guarantor, String> representative;
	public static volatile SingularAttribute<Guarantor, ContactDetails> contactDetails;
}
