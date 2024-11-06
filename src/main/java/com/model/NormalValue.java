package com.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the "Test" database table.
 * 
 */
@Entity
@Table(name="\"NormalValue\"")
@NamedQuery(name="NormalValue.findAll", query="SELECT t FROM NormalValue t")
public class NormalValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column
	private String gender;

	@Column
	private String ageStage;

	@Column
	private double fromRange;
	@Column
	private double toRange;
	
	@ManyToOne
	private Procedure procedure;
	
	
	
	public NormalValue() {
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        NormalValue test = (NormalValue) obj;
        return Objects.equals(id, test.id);
    } 

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeStage() {
		return ageStage;
	}

	public void setAgeStage(String ageStage) {
		this.ageStage = ageStage;
	}

	public double getFromRange() {
		return fromRange;
	}

	public void setFromRange(double fromRange) {
		this.fromRange = fromRange;
	}

	public double getToRange() {
		return toRange;
	}

	public void setToRange(double toRange) {
		this.toRange = toRange;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}


}