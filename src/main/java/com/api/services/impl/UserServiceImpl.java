package com.api.services.impl;

import com.api.model.User;
import com.api.repositories.UserRepository;
import com.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User getUserByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        User user = new User();

        user.setId(optionalUser.get().getId());
        user.setName(optionalUser.get().getName());
        user.setUsername(optionalUser.get().getUsername());
        user.setEmail(optionalUser.get().getEmail());
        user.setPassword(optionalUser.get().getPassword());

        return user;
    }
}