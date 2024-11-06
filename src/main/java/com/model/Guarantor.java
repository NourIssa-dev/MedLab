package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the "Guarantor" database table.
 * 
 */
@Entity
@Table(name="\"Guarantor\"")
@NamedQuery(name="Guarantor.findAll", query="SELECT g FROM Guarantor g")
public class Guarantor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="\"GuarID\"")
	private Integer guarID;

	@Column(name="\"Address\"")
	private String address;

	@Column(name="\"GuarName\"")
	private String guarName;

	@Column(name="\"Representative\"")
	private String representative;
	
	@Embedded
    private ContactDetails contactDetails;



	public Guarantor() {
		contactDetails=new ContactDetails();
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Guarantor guarantor = (Guarantor) obj;
        return Objects.equals(guarID, guarantor.guarID);
    } 

    @Override
    public int hashCode() {
        return Objects.hash(guarID);
    }
	

	public Integer getGuarID() {
		return this.guarID;
	}

	public void setGuarID(Integer guarID) {
		this.guarID = guarID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuarName() {
		return this.guarName;
	}

	public void setGuarName(String guarName) {
		this.guarName = guarName;
	}
	

//	public List<Request> getRequests() {
//		return this.requests;
//	}
//
//	public void setRequests(List<Request> requests) {
//		this.requests = requests;
//	}
//
//	public Request addRequest(Request request) {
//		getRequests().add(request);
//		request.setGuarantor(this);
//
//		return request;
//	}
//
//	public Request removeRequest(Request request) {
//		getRequests().remove(request);
//		request.setGuarantor(null);
//
//		return request;
//	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

}