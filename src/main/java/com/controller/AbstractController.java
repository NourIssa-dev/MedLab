package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.facade.AbstractFacade;


public abstract class AbstractController <T> implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected T entityClass;
    private List <T> entityList=new ArrayList<T>();
    protected AbstractFacade <T> abstractFasade;
    private boolean hidden;


	public AbstractController() {}
    
    
    public AbstractController(T entityClass) {
    	this.entityClass=entityClass;
    }
    public abstract void init();
    
    @Transactional(Transactional.TxType.REQUIRED)
    public T save() {
    	try {
    		entityClass= abstractFasade.save(entityClass);
    		entityList.add(entityClass);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	return entityClass;
    	
    }


//	private void addList(T entityClass2) {
//		// TODO Auto-generated method stub
//		entityList.add(entityClass2);
//	}


	public T find(Object id) {
    	try {
    		return abstractFasade.find(id);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		
		}
    	return null;
    }
    public void remove(T entityClass) {
    	try {
    		abstractFasade.remove(entityClass);
    		entityList.remove(entityClass);
    		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		
	}
    
    
    public List<T> findAll(){
    	try {
    		entityList=abstractFasade.findAll();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return entityList;
    }
    
    
    
    
    


	public T getEntityClass() {
		return entityClass;
	}


	public void setEntityClass(T entityClass) {
		this.entityClass = entityClass;
	}


	public List<T> getEntityList() {
		return entityList;
	}


	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}


	public void setAbstractFasade(AbstractFacade<T> abstractFasade) {
		this.abstractFasade = abstractFasade;
	}


	public AbstractFacade<T> getAbstractFasade() {
		return abstractFasade;
	}

    public void changeHidden() {
    	this.setHidden(true);
    }


	public boolean isHidden() {
		return hidden;
	}


	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	
    
    
    
  
   
    
    
    
}
