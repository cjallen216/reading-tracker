package com.techelevator.dao;

import com.techelevator.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;
import javax.swing.Action;

public class BookSqlDaoIntegrationTest extends DAOIntegrationTest {

    private BookSqlDAO BookSqlDAO;
    private String MESSAGE;
    private String ISBN;
    private String TITLE;
    private String AUTHOR;
    private String IMG;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        BookSqlDAO = new BookSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewBook(){
        // arrange 
    	MESSAGE = "Creating a new book returns true.";
        ISBN = "isbn";
        TITLE = "book_title";
        AUTHOR = "author_name";
        IMG = "img_link";
        boolean expected = true;

        // act
        boolean actual = BookSqlDAO.createBook(AUTHOR, ISBN, TITLE, IMG);

        // assert
        assertEquals(expected, actual, MESSAGE);
    }

}