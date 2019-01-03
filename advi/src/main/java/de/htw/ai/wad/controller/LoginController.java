package de.htw.ai.wad.controller;

import java.io.IOException;

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

import de.htw.ai.wad.dao.IUserDAO;
import de.htw.ai.wad.model.User;

@Controller
public class LoginController {
    
    @Autowired
    IUserDAO userDAO;
	
    // http://localhost:8080/loginMVC/login?userId=admin&password=password
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ResponseEntity<String> loginUser (
	        @RequestParam("userId") String userId, 
	        @RequestParam("password") String password) throws IOException {		

	    //Now: Business logic, verify that user exists...
	    User user = userDAO.getUserByUserId(userId);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    if (user == null || !user.getPassword().equals(password)) {
	        return new ResponseEntity<String>("No such userId/password combo"+Boolean.toString(user==null) , 
	                responseHeaders, HttpStatus.UNAUTHORIZED);
	    } else {
	        responseHeaders.add("Content-Type", "application/json");
	        String json = convertToJson(user);
	        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK); 
	    }		
	}
	
	private String convertToJson(User user) throws JsonProcessingException{
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.writeValueAsString(user); 
	}
}

