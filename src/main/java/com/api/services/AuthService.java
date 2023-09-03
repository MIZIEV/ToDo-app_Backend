package com.api.services;

import com.api.dto.JwtAuthResponse;
import com.api.dto.LoginDto;
import com.api.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}