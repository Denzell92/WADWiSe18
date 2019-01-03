package de.htw.ai.wad.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    
    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setUserId(row.getString("userId"));
        user.setPassword(row.getString("password"));
        user.setFirstname(row.getString("firstName"));
        user.setLastname(row.getString("lastName"));
        user.setIsAdmin(row.getBoolean("isAdmin"));
        return user;
    }
}