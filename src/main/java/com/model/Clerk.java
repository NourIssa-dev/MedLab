package com.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "Patient" database table.
 * 
 */
@Entity
@Table(name = "\"Clerk\"")
@NamedQuery(name = "Clerk.findAll", query = "SELECT c FROM Clerk c")
public class Clerk extends Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ClerkID\"")
	private Integer clerkID;

	@Column(name = "\"Gender\"")
	private String gender;


	@Embedded
	private ContactDetails contactDetails;


	public Clerk() {
		super();
		this.contactDetails = new ContactDetails();
	}

	public Clerk(Integer clerkID, String gender, String phoneNumber, String email) {
		super();
		this.clerkID = clerkID;
		this.gender = gender;
		contactDetails.setPhoneNumber(phoneNumber);
		contactDetails.setEmail(email);

	}

//	public Patient addProfileImage(byte[] image) {
//		this.setProfileImage(image);
//		return this;
//	}

	public Integer getClerkID() {
		return this.clerkID;
	}

	public void setPatientID(Integer clerkID) {
		this.clerkID = clerkID;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}


}