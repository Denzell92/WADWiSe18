package de.htw.ai.wad.model;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet row, int rowNum) throws SQLException {
        Contact c = new Contact();
        c.setId(row.getInt("id"));
        c.setFirstname(row.getString("firstName"));
        c.setLastname(row.getString("lastName"));
        c.setLastname(row.getString("street"));
        c.setLastname(row.getString("plz"));
        c.setLastname(row.getString("town"));
        c.setLastname(row.getString("country"));
        return c;
    }


}
