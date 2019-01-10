package de.htw.ai.wad.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import de.htw.ai.wad.model.User;

@Repository
public class UserDBDAO implements IUserDAO {

    private JdbcTemplate jdbcTemplate;
    
    // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public User getUserByUserId(String userId) {
        try {
            //String sql = "SELECT id, userId, password, firstName, lastName, isAdmin FROM user WHERE userId = ?";
            String sql = "SELECT id, userId, password, firstName, lastName, isAdmin FROM Users WHERE userId = ?";
            RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
            User user = jdbcTemplate.queryForObject(sql, rowMapper, userId);
            return user;
        } catch (DataAccessException dae) {
            System.out.println("Caught: " + dae.getMessage());
            return null;
        }
    }
    
    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void addUser(User User) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateUser(User User) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        
    }
}
