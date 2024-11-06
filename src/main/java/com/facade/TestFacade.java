package com.facade;

import com.github.adminfaces.template.exception.BusinessException;

import com.model.Test;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Stateless
public class TestFacade extends AbstractFacade<Test> implements Serializable {

   
	 public TestFacade() {
	        super(Test.class);
	    }



}
