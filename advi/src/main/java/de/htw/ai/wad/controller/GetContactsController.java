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
public class GetContactsController {

    @Autowired
    IContactsDAO cDAO;

    // http://localhost:8080/loginMVC/login?userId=admin&password=password
    @RequestMapping(value="/getContacts", method=RequestMethod.GET)
    public ResponseEntity<String> getContacts () throws IOException {

        List<Contact> contacts = cDAO.getAllContacts();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");
        String json = convertToJson(contacts);
        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);

    }

    private String convertToJson(List<Contact> cList) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cList);
    }
}
