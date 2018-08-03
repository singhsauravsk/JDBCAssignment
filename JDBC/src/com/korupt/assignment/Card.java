package com.korupt.assignment;

import java.io.Serializable;
import java.sql.Date;

public class Card implements Serializable {
	private static final long serialVersionUID = 7167028758021295521L;
	private long id;
	private Date date;
	private long customer_id;
	private double balance;
	private double credit_limit;
	
	public Card() {
		super();
	}
	
	public Card(long id, Date date, long customer_id, double balance, double credit_limit) {
		super();
		this.id = id;
		this.date = date;
		this.customer_id = customer_id;
		this.balance = balance;
		this.credit_limit = credit_limit;
	}

	long getId() {
		return id;
	}

	void setId(long id) {
		this.id = id;
	}

	Date getDate() {
		return date;
	}

	void setDate(Date date) {
		this.date = date;
	}

	long getCustomer_id() {
		return customer_id;
	}

	void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	double getBalance() {
		return balance;
	}

	void setBalance(double balance) {
		this.balance = balance;
	}

	double getCredit_limit() {
		return credit_limit;
	}

	void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", date=" + date + ", customer_id=" + customer_id + ", balance=" + balance
				+ ", credit_limit=" + credit_limit + "]";
	}
}
