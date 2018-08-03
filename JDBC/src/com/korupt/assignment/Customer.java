package com.korupt.assignment;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 6091976648985943148L;
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Customer() {
		super();
	}
	
	public Customer(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	long getId() {
		return id;
	}

	void setId(long id) {
		this.id = id;
	}

	String getFirstName() {
		return firstName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	String getLastName() {
		return lastName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
