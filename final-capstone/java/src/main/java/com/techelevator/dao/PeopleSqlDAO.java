package com.techelevator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PeopleSqlDAO implements PeopleDAO {
@Autowired
private JdbcTemplate jdbcTemplate;

@Override
public void createPeople(String firstName, String lastName) {

	String sql = "Insert into people (first_name, last_name)"
			+ "Values (?, ?)";
	
	jdbcTemplate.update(sql,firstName, lastName);
	
}



}
