package com.api.services.impl;

import com.api.dto.RegisterDto;
import com.api.model.User;
import com.api.repositories.UserRepository;
import com.api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;

    @Autowired
    public AuthServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String register(RegisterDto registerDto) {

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        repository.save(user);

        return "User registered successfully!!!";
    }
}
