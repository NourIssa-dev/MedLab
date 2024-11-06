package com.facade;

import com.model.Patient;
import javax.ejb.Stateless;
import java.io.Serializable;


@Stateless
public class PatientFacade extends AbstractFacade<Patient> implements Serializable {

   
	 public PatientFacade() {
	        super(Patient.class);
	    }



}
