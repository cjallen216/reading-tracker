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
		boolean userCreated = userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.findByUsername("TEST_USER");
		
		// assert        
        Assert.assertTrue(userCreated);        
        Assert.assertEquals("TEST_USER", user.getUsername(), MESSAGE);
    }
    
    @Test
    public void findByUsername() {
		// arrange
		MESSAGE = "Should return user with matching username";
		
		// act
		userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.findByUsername("TEST_USER");
		
		// assert        
        Assert.assertEquals("TEST_USER", user.getUsername(), MESSAGE);
    }
    
    @Test
    public void findIdByUsername() {
		// arrange    	
		MESSAGE = "Should return user id of user with matching username";
		int nextId = userSqlDAO.findNextUserId();
		
		// act
		userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		int userId = userSqlDAO.findIdByUsername("TEST_USER");
		
		// assert        
        Assert.assertEquals(String.valueOf(nextId), String.valueOf(userId), MESSAGE);
    }
    
    @Test
    public void findByFirstName() {
		// arrange
		MESSAGE = "Should return user with matching first name";
		
		// act
		boolean userCreated = userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.findByFirstName("test_firstName");
		
		// assert
		Assert.assertTrue(userCreated); 
        Assert.assertEquals("test_firstName", user.getFirstName(), MESSAGE);
    }
    
    @Test
    public void findByEmail() {
		// arrange
		MESSAGE = "Should return user with matching email";
		
		// act
		boolean userCreated = userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
        User user = userSqlDAO.findByEmail("test@email.com");
		
		// assert
        Assert.assertTrue(userCreated);
        Assert.assertEquals("test@email.com", user.getEmail(), MESSAGE);
    }
    
    @Test
    public void createNewUserWithoutLastName() {
		// arrange
		MESSAGE = "Should be able to create user with empty string or null last name";
		
		// act
		boolean userCreated = userSqlDAO.create("test_firstName", "", "test@email.com", "TEST_USER","test_password", "user");
        User user = userSqlDAO.findByUsername("TEST_USER");
		
		// assert        
        Assert.assertTrue(userCreated);
        Assert.assertEquals("TEST_USER", user.getUsername(), MESSAGE);        
    }  
}
