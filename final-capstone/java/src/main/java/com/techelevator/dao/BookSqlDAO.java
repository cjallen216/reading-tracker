package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Book;
import com.techelevator.model.User;
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

	@Override
	public List<Book> listAll() {
		List<Book> books = new ArrayList<>();
		String sql = "select book_id,\r\n"
				+ "isbn_number, title, author_people_id, cover_img_link,\r\n"
				+ "p.first_name,\r\n"
				+ "p.last_name\r\n"
				+ "From books as B\r\n"
				+ "LEFT JOIN people as P on b.author_people_id = P.people_id";
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
	        while(results.next()) {
	            Book book = mapRowToBook(results);
	            books.add(book);
	        }
	        return books;
	}
	 private Book mapRowToBook(SqlRowSet rs) {
	        Book book = new Book();
	        book.setBookId(rs.getInt("book_id"));
	        book.setIsbnNumber(rs.getString("isbn_number"));
	        book.setFirstName(rs.getString("first_name"));
	        book.setLastName(rs.getString("last_name"));
	        book.setTitle(rs.getString("title"));
	        book.setImgLink(rs.getString("cover_img_link"));
	        book.setAuthorId(rs.getInt("author_people_id"));
	        return book;
	    }

	@Override
	public Book getBookByID(int bookId) {
		String sql = "select book_id,\r\n"
				+ "isbn_number, title, author_people_id, cover_img_link,\r\n"
				+ "p.first_name,\r\n"
				+ "p.last_name\r\n"
				+ "From books as B\r\n"
				+ "LEFT JOIN people as P on b.author_people_id = P.people_id\r\n"
				+ "WHERE b.book_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
		if(results.next()) {
			return mapRowToBook(results);			
	}else {
		throw new RuntimeException("Book ID "+ bookId +" was not found.");
	}}

	 
	
	
	
	
	

	
	
}
