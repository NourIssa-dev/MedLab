package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-04-10T22:09:23.781+0300")
@StaticMetamodel(Clerk.class)
public class Clerk_ extends Person_ {
	public static volatile SingularAttribute<Clerk, Integer> clerkID;
	public static volatile SingularAttribute<Clerk, String> gender;
	public static volatile SingularAttribute<Clerk, ContactDetails> contactDetails;
}
