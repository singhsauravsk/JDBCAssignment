package com.korupt.assignment;

import java.sql.Date;
import java.util.List;

public class App {
	
	public static void main(String[] args) {
		CustomerDAO customerDAO = new CustomerDAO();
		CardDAO cardDAO = new CardDAO();
		
		for(Long i = 0l; i < 10l; i++) {
			Customer customer = new Customer(1001+i, "Saurav"+i.toString(), "Kumar"+i.toString(), "singhsauravsk"+i.toString());
			Card card = new Card(101+i, Date.valueOf("2018-09-1"+i.toString()), 1001+i, 12323.43+i, 100.00+i);
		
			customerDAO.insertInto(customer);
			cardDAO.insertInto(card);
		}
		
		List <Customer> allCustomer= customerDAO.findAll();
		List <Card> allCard = cardDAO.findAll();
		
		for(Customer cs : allCustomer) {
			System.out.println(cs);
		}
		
		for(Card crd : allCard) {
			System.out.println(crd);
		}
		
		Customer customer = customerDAO.findById(1001l);
		Card card = cardDAO.findById(101l);
		
		System.out.println(customer);
		System.out.println(card);
	}
}