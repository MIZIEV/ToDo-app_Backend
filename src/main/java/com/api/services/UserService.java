package com.api.services;

import com.api.model.User;

public interface UserService {
    User getUserByUsername(String username);
    void updateUser(User editedUser,String username);
    void deleteUser(String username);
}