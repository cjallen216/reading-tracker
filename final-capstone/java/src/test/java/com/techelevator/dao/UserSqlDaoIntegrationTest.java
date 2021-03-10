package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private UserSqlDAO userSqlDAO;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userSqlDAO = new UserSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
        User user = userSqlDAO.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }
    
    @Test
    public void findByFirstName() {
        userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER","test_password","user");
        User user = userSqlDAO.findByFirstName("test_firstName");
        Assert.assertEquals("test_firstName", user.getFirstName());
    }
    
    @Test
    public void findByEmail() {
        userSqlDAO.create("test_firstName", "test_lastName", "test@email.com", "TEST_USER","test_password","user");
        User user = userSqlDAO.findByEmail("test@email.com");
        Assert.assertEquals("test@email.com", user.getEmail());
    }

    
    @Test
    public void createNewUserWithoutLastName() {
        boolean userCreated = userSqlDAO.create("test_firstName", "test", "test@email.com", "TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
        User user = userSqlDAO.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
        
    }
    
    
}
