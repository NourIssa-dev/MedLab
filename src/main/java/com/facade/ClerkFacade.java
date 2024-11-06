package com.facade;

import com.model.Clerk;
import javax.ejb.Stateless;
import java.io.Serializable;


@Stateless
public class ClerkFacade extends AbstractFacade<Clerk> implements Serializable {

   
	 public ClerkFacade() {
	        super(Clerk.class);
	    }



}
