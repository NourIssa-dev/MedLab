package com.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


@Stateless
public class AbstractFacade <T> implements Serializable {
    
	private static final long serialVersionUID = 1L;
	protected Class <T> entityClass;
    @PersistenceContext(unitName = "LabDS")
	protected EntityManager em;
    
    
    public AbstractFacade() {}
    public AbstractFacade(Class <T>entityClass) {
    	this.entityClass=entityClass;
    	
    }
    
    public EntityManager getEntityManager() {
    	return getEm();
    }
    public EntityManager getEm() {
		return em;
	}
	public T save (T entityClass) {
    	return getEntityManager().merge(entityClass);
    }
    public void remove(T entityClass2) {
    	getEntityManager().remove(getEntityManager().merge(entityClass2));
    }
    public T find(Object id) {
		return getEntityManager().find(entityClass, id);
    	
    }
    public List<T> findAll(){
      CriteriaQuery<T> cq= (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
      cq.select(cq.from(entityClass));
      return getEntityManager().createQuery(cq).getResultList();
    }
	
	
}
