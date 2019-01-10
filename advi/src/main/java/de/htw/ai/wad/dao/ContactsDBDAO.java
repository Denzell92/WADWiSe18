package de.htw.ai.wad.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.htw.ai.wad.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import de.htw.ai.wad.model.Contact;

@Repository
public class ContactsDBDAO implements IContactsDAO {

    private JdbcTemplate jdbcTemplate;

    // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Contact getContactById(String cId) {
        //todo
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        int notFound = 0;
        for(int i = 1; i <= 30; i++){
            try {
            //String sql = "SELECT id, userId, password, firstName, lastName, isAdmin FROM user WHERE userId = ?";
            //int i = 1;
                if(notFound >= 4){break;}
                String sql = "SELECT id, firstname, lastname, street, plz, town, country FROM Contacts WHERE id = ?";
                RowMapper<Contact> rowMapper = new BeanPropertyRowMapper<Contact>(Contact.class);
                Contact c = jdbcTemplate.queryForObject(sql, rowMapper,i);
                if(c != null){contacts.add(c);notFound=0;}
            }catch (DataAccessException dae) {
                System.out.println("Caught: " + dae.getMessage());
                notFound++;
               // return contacts;
            }
        }
        return contacts;
    }

    @Override
    public void addContact(Contact c) {
        String sql = "INSERT INTO Contacts (id, firstname, lastname, street, plz, town, country) VALUES ('"+c.getId()+"','"+c.getFirstname()+"','"+c.getLastname()+"','"+c.getStreet()+"','"+c.getPlz()+"','"+c.getTown()+"','"+c.getCountry() +"')  ;";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void updateContact(Contact c) {
        String sql = "UPDATE Contacts SET firstname = '"+c.getFirstname()+"',lastname = '"+c.getLastname()+"',street = '"+c.getStreet()+"',town = '"+c.getTown()+"',plz = '"+c.getPlz()+"',country = '"+c.getCountry()+"'  WHERE id = '"+c.getId()+"';";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void deleteContact(String cID) {
        String sql = "DELETE FROM Contacts WHERE id = '"+cID+"';";
        jdbcTemplate.execute(sql);
    }
}
