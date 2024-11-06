package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the "Test" database table.
 * 
 */
@Entity
@Table(name="\"Category\"")
@NamedQuery(name="Category.findAll", query="SELECT t FROM Category t")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "category")
	private List<Test> tests;

	
	public Category() {
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Category test = (Category) obj;
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

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}