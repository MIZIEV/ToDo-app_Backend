package com.api.services.impl;

import com.api.model.User;
import com.api.repositories.UserRepository;
import com.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        User user = new User();

        if (optionalUser.isPresent()) {

            user.setId(optionalUser.get().getId());
            user.setName(optionalUser.get().getName());
            user.setUsername(optionalUser.get().getUsername());
            user.setEmail(optionalUser.get().getEmail());
            user.setPassword(optionalUser.get().getPassword());
            user.setTodoList(optionalUser.get().getTodoList());
        }

        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUser(User editedUser, String username) {

        Optional<User> optionalUserForUpdating = userRepository.findByUsername(username);

        if (optionalUserForUpdating.isPresent()) {
            optionalUserForUpdating.get().setName(editedUser.getName());
            optionalUserForUpdating.get().setUsername(editedUser.getUsername());
            optionalUserForUpdating.get().setEmail(editedUser.getEmail());
            optionalUserForUpdating.get().setPassword(passwordEncoder.encode(editedUser.getPassword()));

            userRepository.save(optionalUserForUpdating.get());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(String username) {
        Optional<User> optionalUserForDeleting = userRepository.findByUsername(username);

        if (optionalUserForDeleting.isPresent()) {
            userRepository.delete(optionalUserForDeleting.get());
        }
    }

}