package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the "Doctor" database table.
 * 
 */
@Entity
@Table(name="\"Doctor\"")
@NamedQuery(name="Doctor.findAll", query="SELECT d FROM Doctor d")
public class Doctor extends Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="\"DoctorID\"")
	private Integer doctorID;

	
	@Column(name="\"Speciality\"")
	private String speciality;

	public Doctor() {
	}

	public Integer getDoctorID() {
		return this.doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Doctor guarantor = (Doctor) obj;
        return Objects.equals(doctorID, guarantor.doctorID);
    } 

    @Override
    public int hashCode() {
        return Objects.hash(doctorID);
    }
	

	
}