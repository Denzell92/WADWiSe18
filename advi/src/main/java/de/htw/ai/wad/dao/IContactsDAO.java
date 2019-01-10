package de.htw.ai.wad.dao;

import de.htw.ai.wad.model.Contact;

import java.util.List;

public interface IContactsDAO {
    Contact getContactById(String cId);
    List<Contact> getAllContacts();
    void addContact(Contact c);
    void updateContact(Contact c);
    void deleteContact(String cId);
}
