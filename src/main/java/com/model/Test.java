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
@Table(name="\"Test\"")
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(name="\"TestCode\"")
	private String testCode;

	@Column(name="\"Price\"")
	private double price;

	@Column(name="\"TestName\"")
	private String testName;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "test")
	private List<Procedure> procedures;
	
	
	public Test() {
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Test test = (Test) obj;
        return Objects.equals(id, test.id);
    } 

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	public void addToProcedures(Procedure p) {
		this.procedures.add(p);
		
	}


}