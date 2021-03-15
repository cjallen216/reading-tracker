package com.techelevator.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.techelevator.model.Book;

import com.techelevator.model.Book;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

public class BookSqlDaoIntegrationTest extends DAOIntegrationTest {

    private BookSqlDAO bookSqlDAO;
    private JdbcTemplate jdbcTemplate;
    private int BOOK_ID;
    private String MESSAGE;
    private String ISBN;
    private String TITLE;
    private String AUTHOR;
    private String IMG;
    private int USER_ID = 2;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        bookSqlDAO = new BookSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewBookReturnsCreatedBook(){
        // arrange 
    	TITLE = "TITLE_createNewBookReturnsTrue";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz";
        Book expected = new Book(BOOK_ID, ISBN, TITLE, AUTHOR, IMG, false, false);

        // act
        Book actual = bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);

        // assert
        assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
        assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
        assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
        assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);
    }
    
    @Test
    public void createNewBookUpdatesBooksUsersTable(){
        // arrange         
    	MESSAGE = "Creating a new book updates the books_users table.";
    	TITLE = "TITLE_createNewBookUpdatesBooksUsersTable";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz";                
        
        // act
        bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);
        Book book = bookSqlDAO.getBookByTitle(TITLE);
        BOOK_ID = book.getBookId();        
        int bookUserId = bookSqlDAO.getBookUserId(BOOK_ID, USER_ID);
        
        // assert
        assertNotEquals(0, bookUserId, MESSAGE);
    }
    
    @Test
    public void getBookById(){
        // arrange         
    	TITLE = "TITLE_getBooksById";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	
        bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);
        Book book = bookSqlDAO.getBookByTitle(TITLE);
        BOOK_ID = book.getBookId();

        // act
       Book actual = bookSqlDAO.getBookByID(BOOK_ID);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);
       assertEquals(BOOK_ID, actual.getBookId(), "The book id should be " + BOOK_ID);
    }
    
    @Test
    public void getBooksByUserId(){
        // arrange    
    	MESSAGE = "Returns the size of the user's book list";
        for (int i = 0; i < 5; i++) {        	
        	TITLE = "TITLE" + i;
        	AUTHOR = "AUTHOR";
        	ISBN = "ISBN" + i;
        	IMG = "abcefghijklmnopqrstuvwxyz";
        	
        	bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);
    	}
    	
    	int expectedCount = 5;

        // act
    	List<Book> actual = bookSqlDAO.getBooksByUserId(USER_ID);

        // assert
        assertEquals(expectedCount, actual.size(), MESSAGE);
    }
    
    @Test
    public void getBookByIsbn() {
        // arrange 
    	TITLE = "TITLE_getBookByIsbn";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);

        // act
       Book actual = bookSqlDAO.getBookByIsbn(ISBN);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);    
    }
    
    @Test
    public void getBookByTitle() {
        // arrange 
    	TITLE = "TITLE_getBookByTitle";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	bookSqlDAO.createBook(TITLE, AUTHOR, ISBN, IMG, USER_ID);

        // act
       Book actual = bookSqlDAO.getBookByTitle(TITLE);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);     
    }
}