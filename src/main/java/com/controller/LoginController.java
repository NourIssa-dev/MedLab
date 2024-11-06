package com.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.security.Users;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String originalURL;
	private boolean loggedin = false;

	@Inject
	protected UserFacade userFacade;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

		if (originalURL == null) {

		} else {
			String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}

	}

	public void login() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		Users user = userFacade.find(username);
//		System.out.println(user.getListUsersPermissions().get(0).getPermission().getPermissionName());
//		if(user!=null) {
//			if (user.getListUsersPermissions().get(0).getPermission().getPermissionName().equals("admin")) {
//				originalURL = "/project/view/admin/admin-dashboard.xhtml";
//			} else if (user.getListUsersPermissions().get(0).getPermission().getPermissionName().equals("employee")) {
//				originalURL = "/project/view/employee/employee-dashboard.xhtml";
//			} else if (user.getListUsersPermissions().get(0).getPermission().getPermissionName().equals("customer")) {
//				originalURL = "/project/view/customer/customer.xhtml";
//			}
//		}

		try {
			request.login(username, password);
		} catch (Exception e) {
			if(e.getMessage().contains("Login failed")){
				originalURL="/project/resources/error.xhtml";
			}
		}
		try {
			loggedin = true;
			externalContext.redirect(originalURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		try {
			request.logout();
			loggedin=false;
			externalContext.redirect("/project/home/home.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
			// send message with error
		}

	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}