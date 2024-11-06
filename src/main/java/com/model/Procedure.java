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
@Table(name="\"Procedure\"")
@NamedQuery(name="Procedure.findAll", query="SELECT t FROM Procedure t")
public class Procedure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column
	private String name;
	
	@ManyToOne
	private Test test;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "procedure")
	private List<NormalValue> normalValues=new ArrayList<NormalValue>();
	
	public Procedure() {
	}
	
	public Procedure(String name) {
		this.setName(name);
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Procedure test = (Procedure) obj;
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

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NormalValue> getNormalValues() {
		return normalValues;
	}

	public void setNormalValues(List<NormalValue> normalValues) {
		this.normalValues = normalValues;
	}



}