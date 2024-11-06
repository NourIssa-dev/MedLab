package com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-03-09T14:15:43.400+0200")
@StaticMetamodel(Patient.class)
public class Patient_ extends Person_ {
	public static volatile SingularAttribute<Patient, Integer> patientID;
	public static volatile SingularAttribute<Patient, Integer> age;
	public static volatile SingularAttribute<Patient, Date> dateOfBirth;
	public static volatile SingularAttribute<Patient, String> gender;
	public static volatile SingularAttribute<Patient, ContactDetails> contactDetails;
	public static volatile ListAttribute<Patient, Request> requests;
}
