package de.htw.ai.wad.dao;

import java.util.List;

import de.htw.ai.wad.model.User;

public interface IUserDAO {
    User getUserByUserId(String userId);
    List<User> getAllUsers();
    void addUser(User User);
    void updateUser(User User);
    void deleteUser(String userId);
}
