package com.facade;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.model.Category;


@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements Serializable {

   
	 public CategoryFacade() {
	        super(Category.class);
	    }



}
