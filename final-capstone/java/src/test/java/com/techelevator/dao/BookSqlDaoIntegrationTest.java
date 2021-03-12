package com.techelevator.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.sql.DataSource;

public class BookSqlDaoIntegrationTest extends DAOIntegrationTest {

    private BookSqlDAO bookSqlDAO;
    private JdbcTemplate jdbcTemplate;
    private String MESSAGE;
    private String ISBN;
    private String TITLE;
    private String AUTHOR;
    private String IMG;
    private int USER_ID;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        bookSqlDAO = new BookSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewBookReturnsTrue(){
        // arrange 
    	MESSAGE = "Creating a new book returns true.";
        ISBN = "isbn";
        TITLE = "book_title";
        AUTHOR = "author_name";
        IMG = "img_link";
        USER_ID = 1;
        boolean expected = true;

        // act
        boolean actual = bookSqlDAO.createBook(AUTHOR, ISBN, TITLE, IMG, USER_ID);

        // assert
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void createNewBookUpdatesBooksUsersTable(){
        // arrange 
        String userIdSql = "SELECT user_id from users where username = ?";
        SqlRowSet userIdResult = jdbcTemplate.queryForRowSet(userIdSql, "family_reading_tracker_appuser");
        int user_id = 0;
        if(userIdResult.next()) {
        	user_id = userIdResult.getInt("user_id");
        }
        
        ISBN = "isbn";
        TITLE = "book_title";
        AUTHOR = "author_name";
        IMG = "img_link";
        bookSqlDAO.createBook(AUTHOR, ISBN, TITLE, IMG, user_id);               
        
        String BookIdSql = "SELECT book_id FROM books WHERE title = ? AND author = ? AND isbn = ?";
        SqlRowSet bookIdResult = jdbcTemplate.queryForRowSet(BookIdSql, TITLE, AUTHOR, ISBN);
        int bookId = 0;
        if(bookIdResult.next()) {
        	bookId = bookIdResult.getInt("book_id");
        }

        // act
        String bookUserSql = "SELECT books_users_id, book_id, user_id FROM books_users WHERE book_id = ? AND user_id = ?";
        SqlRowSet booksUsersResult = jdbcTemplate.queryForRowSet(bookUserSql, bookId, user_id);
        int bookUserId = 0;
        if(booksUsersResult.next()) {
        	bookUserId = booksUsersResult.getInt("books_users_id");
        }
        
        // assert
        assertNotEquals(0, bookUserId);
        assertNotNull(bookUserId);
    }

}