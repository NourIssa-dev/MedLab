package com.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person {
	
		@Column(name="\"Fname\"")
		private String fname;

		@Column(name="\"Mname\"")
		private String mname;
		
		@Column(name="\"Lname\"")
		private String lname;
		
		@Column(name="\"Address\"")
	    private String address;
		
	    
		public Person() {
			
		}
		
		
		public Person(String fname, String mname, String lname, String address, ContactDetails contactDetails) {
			this.fname = fname;
			this.mname = mname;
			this.lname = lname;
			this.address = address;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getMname() {
			return mname;
		}
		public void setMname(String mname) {
			this.mname = mname;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getFullName() {
			return fname+ " "+mname+" "+lname;
		}
		
		
	    
}
