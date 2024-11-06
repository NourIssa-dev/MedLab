package com.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-01-17T14:40:09.764+0200")
@StaticMetamodel(Doctor.class)
public class Doctor_ extends Person_ {
	public static volatile SingularAttribute<Doctor, Integer> doctorID;
	public static volatile SingularAttribute<Doctor, String> speciality;
}
