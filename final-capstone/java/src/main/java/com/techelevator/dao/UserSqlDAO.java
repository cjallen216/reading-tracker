package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "select user_id "
        		+ "        , username "
        		+ "        , password_hash "
        		+ "        , role "
        		+ "        , p.first_name "
        		+ "        , p.last_name "
        		+ "        , email  "
        		+ "FROM users AS U "
        		+ "LEFT JOIN people AS P ON u.people_id = p.people_id "
				+ "WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select user_id "
        		+ "        , username "
        		+ "        , password_hash "
        		+ "        , role "
        		+ "        , p.first_name "
        		+ "        , p.last_name "
        		+ "        , email  "
        		+ "FROM users AS U "
        		+ "LEFT JOIN people AS P ON u.people_id = p.people_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }    
    
    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if(user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + email + " was not found.");
    }
    @Override
    public User findByFirstName(String firstName) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if(user.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + firstName + " was not found.");
    }

    @Override
    public boolean create(String firstName, String lastName, String email, String username, String password, String role) {
        boolean userCreated = false;
	     
        // create people record
        String nextValPeopleID = "select nextVal('people_id');";
        SqlRowSet nextPeopleId = jdbcTemplate.queryForRowSet(nextValPeopleID);
        nextPeopleId.next();
        int peopleId = nextPeopleId.getInt("people_id");
        
        String insertPeopleString = "insert into people (people_id, first_name, last_name) values(?,?,?)";
        jdbcTemplate.update(insertPeopleString, nextPeopleId, firstName, lastName);

        // create user
        String insertUser = "insert into users (username,password_hash,role,people_id,email) values(?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();        

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setInt(4, peopleId);
                    ps.setString(5,  email);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setActivated(true);
        return user;
    }
}
