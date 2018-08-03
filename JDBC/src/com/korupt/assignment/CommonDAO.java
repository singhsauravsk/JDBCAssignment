package com.korupt.assignment;

import java.util.List;

public interface CommonDAO <T> {

	void insertInto(T object);
	List <T> findAll();
	T findById(Long id);
}
