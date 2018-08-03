package com.korupt.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements CommonDAO <Card> {
	private static final String CREATE_TABLE = "create table Card(id numeric(11) primary key, expiry DATE, customer_id numeric(11), "
												+ "balance double, credit_limit double,"
												+ "CONSTRAINT cst_id_fr FOREIGN KEY (customer_id) REFERENCES Customer(id))";
	private static final String SELECT_ALL = "select id, expiry, customer_id, balance, credit_limit from Card";
	private static final String SELECT_ID = "select id, expiry, customer_id, balance, credit_limit from Card where id = ";
	private static final String INSERT = "INSERT into Card VALUES(?,?,?,?,?)";
	private static boolean flag = true;
	
	@Override
	public void insertInto(Card card) {
		
		if(flag) {
			
			try(Connection con = ConnectionUtil.getConnection()) {
				Statement st = con.createStatement();
				st.execute(CREATE_TABLE);
				st.close();
			} catch (SQLException e) {
				System.out.println("Card table already exist");
			}
			
			flag = false;
		}
		
		try(Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(INSERT);
			
			ps.setLong(1, card.getId());
			ps.setDate(2, card.getDate());
			ps.setLong(3, card.getCustomer_id());
			ps.setDouble(4, card.getBalance());
			ps.setDouble(5, card.getCredit_limit());
			
			int rows = ps.executeUpdate();
			System.out.println("Card : Record added : " + rows);
			
			ps.close();
		}catch(SQLException ex) {
			System.out.println("Card : This id already exist");
		}
	}

	@Override
	public List <Card> findAll() {
		List <Card> allCard = new ArrayList<>();
		
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Card retrievedCard = new Card();
				retrievedCard.setId(rs.getLong("id"));
				retrievedCard.setDate(rs.getDate("expiry"));
				retrievedCard.setCustomer_id(rs.getLong("customer_id"));
				retrievedCard.setBalance(rs.getDouble("balance"));
				retrievedCard.setCredit_limit(rs.getDouble("credit_limit"));
				allCard.add(retrievedCard);
			}

			return allCard;
		} catch(SQLException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	public Card findById(Long id) {
		
		try(Connection con = ConnectionUtil.getConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ID+id.toString());
			ResultSet rs = ps.executeQuery();
			Card retrievedCard = new Card();
			
			if(rs.next()) {
				retrievedCard.setId(rs.getLong("id"));
				retrievedCard.setDate(rs.getDate("expiry"));
				retrievedCard.setCustomer_id(rs.getLong("customer_id"));
				retrievedCard.setBalance(rs.getDouble("balance"));
				retrievedCard.setCredit_limit(rs.getDouble("credit_limit"));
			}

			return retrievedCard;
		} catch(SQLException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
}
