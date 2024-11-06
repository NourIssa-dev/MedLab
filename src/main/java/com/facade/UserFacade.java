package com.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;


import com.model.security.Users;

@Stateless
public class UserFacade extends AbstractFacade<Users>{

	private static final long serialVersionUID = -1L;

	public UserFacade() {
		super(Users.class);
	}




	

//	public void saveCustomer(Users user,Customer customer){
//		save(user);
//		customerFacade.save(customer);
//		Permission pr = permissionFacade.find(3);
//		UserPermission up = new UserPermission(pr,user);
//		userPermissionFacade.save(up);
//	}
//
//	public void saveEmployee(Users user, Employee  employee){
//		save(user);
//		employeeFacade.save(employee);
//		Permission pr = permissionFacade.find(2);
//		UserPermission up = new UserPermission(pr,user);
//		userPermissionFacade.save(up);
//	}

//	public int findUserByUsername(String username, String oldPassword, String newPassword) {
//		try {
//			return getEntityManager().createNamedQuery(Users.USER_CHANGE_PASS).setParameter("username", username)
//					.setParameter("oldPassword", oldPassword).setParameter("newPassword", newPassword)
//
//					.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//	public Users findUserByUsername(String username) {
//		try {
//			return (Users) getEntityManager().createNamedQuery(Users.SELECT_USER)
//					.setParameter("username", username).getSingleResult();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

}
