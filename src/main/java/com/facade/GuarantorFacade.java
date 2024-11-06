/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.github.adminfaces.template.exception.BusinessException;

import com.model.Guarantor;

import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Stateless
public class GuarantorFacade extends AbstractFacade<Guarantor> implements Serializable {

   
	 public GuarantorFacade() {
	        super(Guarantor.class);
	    }



}
