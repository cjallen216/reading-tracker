package com.techelevator.dao;

import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
@Service
public class BookSqlDAO implements BookDAO{
	 private JdbcTemplate jdbcTemplate;

	    public BookSqlDAO(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	@Override
	public void createBook(String firstName, String lastName, String isbnNumber, String title, String imgLink, int authorId) {
    	String insertPeople = "insert into people (first_name, last_name) values(?,?)";
    	GeneratedKeyHolder peopleKeyHolder = new GeneratedKeyHolder();
        String people_id_column = "people_id";
        boolean peopleCreated = false;
        peopleCreated = jdbcTemplate.update(con -> {
        	PreparedStatement prepared = con.prepareStatement(insertPeople, new String[]{people_id_column});
        		prepared.setString(1, firstName);
        		prepared.setString(2, lastName);
        		return prepared;
        }
        , peopleKeyHolder) == 1;
        int newPeopleId = (int) peopleKeyHolder.getKeys().get(people_id_column);
        String insertBooks = "insert into books (isbn_number, title, author_people_id, cover_img_link ) values (?,?,?,?)";
    	jdbcTemplate.update(insertBooks, isbnNumber, title, newPeopleId, imgLink);
    	
	}
	

	
	
}
