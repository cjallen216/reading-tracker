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
		boolean userCreated = userSqlDAO.createUser("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.getUserByUsername("TEST_USER");
		
		// assert        
        Assert.assertTrue(userCreated);        
        Assert.assertEquals(MESSAGE, "TEST_USER", user.getUsername());
    }
    
    @Test
    public void getUserByUsername() {
		// arrange
		MESSAGE = "Should return user with matching username";
		
		// act
		userSqlDAO.createUser("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.getUserByUsername("TEST_USER");
		
		// assert        
        Assert.assertEquals(MESSAGE, "TEST_USER", user.getUsername());
    }
    
    @Test
    public void getUserIdByUsername() {
		// arrange    	
		MESSAGE = "Should return user id of user with matching username";
		int nextId = userSqlDAO.getNextUserId();
		
		// act
		userSqlDAO.createUser("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		int userId = userSqlDAO.getUserIdByUsername("TEST_USER");
		
		// assert        
        Assert.assertEquals(MESSAGE, String.valueOf(nextId), String.valueOf(userId));
    }
    
    @Test
    public void getUserByFirstName() {
		// arrange
		MESSAGE = "Should return user with matching first name";
		
		// act
		boolean userCreated = userSqlDAO.createUser("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
		User user = userSqlDAO.getUserByFirstName("test_firstName");
		
		// assert
		Assert.assertTrue(userCreated); 
        Assert.assertEquals(MESSAGE, "test_firstName", user.getFirstName());
    }
    
    @Test
    public void getUserByEmail() {
		// arrange
		MESSAGE = "Should return user with matching email";
		
		// act
		boolean userCreated = userSqlDAO.createUser("test_firstName", "test_lastName", "test@email.com", "TEST_USER", "test_password", "user");
        User user = userSqlDAO.getUserByEmail("test@email.com");
		
		// assert
        Assert.assertTrue(userCreated);
        Assert.assertEquals(MESSAGE, "test@email.com", user.getEmail());
    }
    
    @Test
    public void createNewUserWithoutLastName() {
		// arrange
		MESSAGE = "Should be able to create user with empty string or null last name";
		
		// act
		boolean userCreated = userSqlDAO.createUser("test_firstName", "", "test@email.com", "TEST_USER","test_password", "user");
        User user = userSqlDAO.getUserByUsername("TEST_USER");
		
		// assert        
        Assert.assertTrue(userCreated);
        Assert.assertEquals(MESSAGE, "TEST_USER", user.getUsername());        
    }  
}
