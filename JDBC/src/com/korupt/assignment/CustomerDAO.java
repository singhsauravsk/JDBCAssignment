package com.korupt.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CommonDAO <Customer> {
	private static final String CREATE_TABLE = "create table Customer(id numeric(11) primary key, firstName varchar(20), lastName varchar(20), email varchar(20))";
	private static final String SELECT_ALL = "select id, firstName, lastName, email from Customer";
	private static final String SELECT_ID = "select id, firstName, lastName, email from Customer where id = ?";
	private static final String INSERT = "INSERT into Customer VALUES(?,?,?,?)";
	private static boolean flag = true;

	@Override
	public void insertInto(Customer object) {
		
		if(flag) {
			
			try(Connection con = ConnectionUtil.getConnection()) {
				Statement st = con.createStatement();
				st.execute(CREATE_TABLE);
				st.close();
			} catch (SQLException e) {
				System.out.println("Customer table already exist");
			}
			
			flag = false;
		}
		
		try(Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(INSERT);
			
			ps.setLong(1, object.getId());
			ps.setString(2, object.getFirstName());
			ps.setString(3, object.getLastName());
			ps.setString(4, object.getEmail());
			
			int rows = ps.executeUpdate();
			System.out.println("Customer : Record added : " + rows);
			
			ps.close();
		}catch(SQLException ex) {
			System.out.println("Customer :This id already exist");
		}
		
	}

	@Override
	public List <Customer> findAll() {
		List <Customer> allCustomer = new ArrayList<>();
		
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer retrievedCustomer = new Customer();
				retrievedCustomer.setId(rs.getLong("id"));
				retrievedCustomer.setFirstName(rs.getString("firstName"));
				retrievedCustomer.setLastName(rs.getString("lastName"));
				retrievedCustomer.setEmail(rs.getString("email"));
				allCustomer.add(retrievedCustomer);
			}

			return allCustomer;
		} catch(SQLException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Customer findById(Long id) {
		
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			Customer retrievedCustomer = new Customer();
			
			if(rs.next()) {
				retrievedCustomer.setId(rs.getLong("id"));
				retrievedCustomer.setFirstName(rs.getString("firstName"));
				retrievedCustomer.setLastName(rs.getString("lastName"));
				retrievedCustomer.setEmail(rs.getString("email"));
			}

			return retrievedCustomer;
		} catch(SQLException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

}
