package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.translation.messages_bg;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private UserSqlDAO userSqlDAO;
	private String MESSAGE;
	private String FIRST_NAME = "test_firstName";
	private String LAST_NAME = "test_lastName";
	private String EMAIL = "test@email.com";
	private String USERNAME = "TEST_USER";
	private String PASSWORD = "test_password";
	private String ROLE = "user";
	

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userSqlDAO = new UserSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
		// arrange
		MESSAGE = "Should return true if user created in database";	
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByUsername(USERNAME);
		String actual = user.getUsername();
		
		// assert        
        Assert.assertTrue(userCreated);        
        Assert.assertEquals(USERNAME, actual);
    }
    
    @Test
    public void findByUsername() {
		// arrange
		MESSAGE = "Should return user with matching username";
		
		// act
		userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByUsername(USERNAME);
		String actual = user.getUsername();
		
		// assert        
        Assert.assertEquals(USERNAME, actual);
    }
    
    @Test
    public void findIdByUsername() {
		// arrange    	
		MESSAGE = "Should return user id of user with matching username";
		int nextId = userSqlDAO.getNextUserId();
		
		// act
		userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		int userId = userSqlDAO.getUserIdByUsername(USERNAME);
		
		// assert        
        Assert.assertEquals(String.valueOf(nextId), String.valueOf(userId));
    }
    
    @Test
    public void findByFirstName() {
		// arrange
		MESSAGE = "Should return user with matching first name";
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByFirstName(FIRST_NAME);
		String actual = user.getFirstName();
		
		// assert
		Assert.assertTrue(userCreated); 
        Assert.assertEquals(FIRST_NAME, actual);
    }
    
    @Test
    public void findByEmail() {
		// arrange
		MESSAGE = "Should return user with matching email";
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
        User user = userSqlDAO.getUserByEmail(EMAIL);
        String actual = user.getEmail();
		
		// assert
        Assert.assertTrue(userCreated);
        Assert.assertEquals(EMAIL, actual);
    }
    
    @Test
    public void createNewUserWithoutLastName() {
		// arrange
		MESSAGE = "Should be able to create user with empty string or null last name";
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
        User user = userSqlDAO.getUserByUsername(USERNAME);
        String actual = user.getUsername();
		
		// assert        
        Assert.assertTrue(userCreated);
        Assert.assertEquals(USERNAME, actual);        
    }  
}
