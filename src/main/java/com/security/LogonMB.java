package com.security;

import com.facade.RequestFacade;
import com.github.adminfaces.template.session.AdminSession;
import com.model.Patient;
import com.model.Request;

import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;

@Named
@SessionScoped
@Specializes
public class LogonMB extends AdminSession implements Serializable {

	private String currentUser;
	private String email;
	private String password;
	private boolean remember;
	private Integer requestID;

	private List<Integer> requestsIDsList = new ArrayList<>();

	@Inject
	RequestFacade requestFacade;

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			requestsIDsList = requestFacade.findAll().stream().map(Request::getRequestID).collect(Collectors.toList());
			if (requestID != null && requestsIDsList.contains(requestID)) {
				// Authentication succeeded using requestID
				currentUser = "user_with_requestID_" + requestID;
				addDetailMessage("Logged in successfully with requestID <b>" + requestID + "</b>");
				Faces.getExternalContext().getFlash().setKeepMessages(true);
				//requestFacade.getRequestByRequestId(requestID));
				request.login("patient", "123456");
				Faces.redirect("results-page.xhtml?id="+requestID );
			} else {
				request.login(email, password);
				// Authentication succeeded, redirect to a secure page
				currentUser = email;
				addDetailMessage("Logged in successfully as <b>" + email + "</b>");
				Faces.getExternalContext().getFlash().setKeepMessages(true);
				Faces.redirect("index.xhtml");
			}
		} catch (Exception e) {
			// Authentication failed, show error message
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "login failed!", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return;
		}
	}
	
	public void redirectToNextPage(Request r) {
		// Set a parameter in the Flash scope
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("requestID", r.getRequestID());

		// Add a faces message (optional)
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Redirected", null));

		// Perform the redirect
		Faces.redirect("results-page.xhtml?faces-redirect=true");
	}

	@Override
	public boolean isLoggedIn() {

		return currentUser != null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public Integer getRequestID() {
		return requestID;
	}

	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}
}
