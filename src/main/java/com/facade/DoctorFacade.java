package com.facade;

import com.github.adminfaces.template.exception.BusinessException;
import com.model.Doctor;

import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Stateless
public class DoctorFacade extends AbstractFacade<Doctor> implements Serializable {

   
	 public DoctorFacade() {
	        super(Doctor.class);
	    }



}
