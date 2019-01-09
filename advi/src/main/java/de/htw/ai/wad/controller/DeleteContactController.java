package de.htw.ai.wad.controller;

import de.htw.ai.wad.dao.IContactsDAO;
import de.htw.ai.wad.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class DeleteContactController {

	@Autowired
	IContactsDAO cDAO;
	
    // http://localhost:8080/loginMVC/login?userId=admin&password=password
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ResponseEntity<String> addContact (
	        @RequestParam("userID") String uID

	) throws IOException {

	    cDAO.deleteContact(uID);
	    boolean e = true;
	    HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<String>("No such userId/password combo", responseHeaders, HttpStatus.UNAUTHORIZED);

	}
	

}

