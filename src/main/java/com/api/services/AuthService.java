package com.api.services;

import com.api.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
}