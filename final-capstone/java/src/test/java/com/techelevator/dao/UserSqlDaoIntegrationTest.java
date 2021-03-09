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
        boolean userCreated = userSqlDAO.create("Arthur", "Summers", "Arthur.summers.jd@gmail.com", "TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
        User user = userSqlDAO.findByEmail("Arthur.summers.jd@gmail.com");
        Assert.assertEquals("Arthur.summers.jd@gmail.com", user.getEmail());
    }
    
    @Test
    public void createNewUserName() {
        boolean userCreated = userSqlDAO.create("Arthur", "Summers", "Arthur.summers.jd@gmail.com", "TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
    }

}
