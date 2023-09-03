package com.api.services.impl;

import com.api.dto.JwtAuthResponse;
import com.api.dto.LoginDto;
import com.api.dto.RegisterDto;
import com.api.model.User;
import com.api.repositories.UserRepository;
import com.api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(RegisterDto registerDto) {

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        repository.save(user);

        return "User registered successfully!!!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        return null;
    }
}
