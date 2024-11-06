package com.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-12-25T15:39:00.157+0200")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Integer> patientID;
	public static volatile SingularAttribute<Patient, Integer> ageRange;
	public static volatile SingularAttribute<Patient, Date> dateOfBirth;
	public static volatile SingularAttribute<Patient, String> gender;
	public static volatile SingularAttribute<Patient, byte[]> profilePic;
	public static volatile ListAttribute<Patient, Request> requests;
}
