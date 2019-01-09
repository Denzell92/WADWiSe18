package de.htw.ai.wad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.htw.ai.wad.dao.IContactsDAO;
import de.htw.ai.wad.model.Contact;

@Controller
public class AddNewContactController {

	@Autowired
	IContactsDAO cDAO;
	
    // http://localhost:8080/loginMVC/login?userId=admin&password=password
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> addContact (
	        @RequestParam("firstname") String fName,
	        @RequestParam("lastname") String lName,
			@RequestParam("street") String street,
			@RequestParam("town") String town,
			@RequestParam("plz") String plz,
			@RequestParam("country") String country

	) throws IOException {

	    Contact con = new Contact();
		con.setPlz(plz);
		con.setFirstname(fName);
		con.setLastname(lName);
		con.setCountry(country);
		con.setTown(town);
		con.setStreet(street);


	    cDAO.addContact(con);
	    boolean e = true;
	    HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<String>("Success!",
				responseHeaders, HttpStatus.OK);

	}
	

}

