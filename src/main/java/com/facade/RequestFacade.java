package com.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import com.enums.PayType;
import com.model.Doctor;
import com.model.Request;
import com.model.RequestDetail;
import com.model.RequestPayment;
import com.model.Test;

@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class RequestFacade extends AbstractFacade<Request> implements Serializable {

	public RequestFacade() {
		super(Request.class);

	}
	
	@PersistenceContext
    private EntityManager entityManager;
    
    @Resource
    private UserTransaction userTransaction;

    
	  @TransactionAttribute(TransactionAttributeType.REQUIRED)
	    public Request save(Request req) {
		  try {
	            userTransaction.begin();
	            
	            Request mergedRequest = entityManager.merge(req);
	            entityManager.merge(req.getPatient());

	            userTransaction.commit();
	            
	            return mergedRequest;
	        } catch (Exception e) {
	            try {
	                if (userTransaction != null && userTransaction.getStatus() == Status.STATUS_ACTIVE) {
	                    userTransaction.rollback();
	                }
	            } catch (Exception rollbackException) {
	                // Handle rollback exception
	            }
	            throw new RuntimeException("Error editing phone number", e);
	        }
	  }
	  public List<RequestDetail> findByTest(Test test){
		  return getEntityManager().createNamedQuery(RequestDetail.FindByTest, RequestDetail.class)
					.setParameter("test", test.getTestCode()).getResultList();
	  }

	public List<RequestPayment> getPaymentsByConditions(Date fromDate, Date toDate, PayType kind, Doctor doctor) {
		return getEntityManager().createNamedQuery(RequestPayment.FindByConditions, RequestPayment.class)
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate).setParameter("kind", kind)
				.setParameter("doctor", doctor.getDoctorID()).getResultList();
	}

	
	public List<RequestPayment> getPaymentsByConditions2(Date date, PayType kind) {
		return getEntityManager().createNamedQuery(RequestPayment.FindByConditions2, RequestPayment.class)
				.setParameter("month", date.getMonth()+1).setParameter("year", date.getYear()+1900).setParameter("kind", kind)
				.getResultList();
	}
	
	 public Request getRequestByRequestId(Integer requestID) {
		 if(requestID!=0) {
	        TypedQuery<Request> query = em.createQuery(
	                "SELECT r FROM Request r WHERE r.requestID = :requestID",
	                Request.class);
	        query.setParameter("requestID", requestID);
	        return query.getSingleResult();
		 }
		 return null;
	    }
}
