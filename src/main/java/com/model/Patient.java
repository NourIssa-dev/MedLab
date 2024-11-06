package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the "Patient" database table.
 * 
 */
@Entity
@Table(name = "\"Patient\"")
@NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
public class Patient extends Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"PatientID\"")
	private Integer patientID;

	@Column(name = "\"Age\"")
	private Integer age;

	@Temporal(TemporalType.DATE)
	@Column(name = "\"DateOfBirth\"")
	private Date dateOfBirth;

	@Column(name = "\"Gender\"")
	private String gender;


	@Embedded
	private ContactDetails contactDetails;

	// added
	@OneToMany(mappedBy = "patient")
	private List<Request> requests;

	public Patient() {
		super();
		this.contactDetails = new ContactDetails();
	}

	public Patient(Integer patientID, Integer age, Date dateOfBirth, String gender, List<Request> requests,
			String phoneNumber, String email) {
		super();
		this.patientID = patientID;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.requests = requests;
		contactDetails.setPhoneNumber(phoneNumber);
		contactDetails.setEmail(email);

	}


	public Integer getPatientID() {
		return this.patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		calculateAge();
	}

	private void calculateAge() {
		Date currentDate = new Date();
		long timeDifference = currentDate.getTime() - dateOfBirth.getTime();
		long ageInYears = timeDifference / (365L * 24 * 60 * 60 * 1000);

		setAge((int) ageInYears);
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

//	public byte[] getProfileImage() {
//		return profileImage;
//	}
//
//	public void setProfileImage(byte[] profileImage) {
//		this.profileImage = profileImage;
//	}

}