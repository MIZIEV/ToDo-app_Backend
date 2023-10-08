package com.api.controllers;


import com.api.dto.RegisterDto;
import com.api.model.User;
import com.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public RegisterDto getUser(@PathVariable String username) {
        RegisterDto userDto = convertToUserDto(userService.getUserByUsername(username));
        return userDto;
    }

    private RegisterDto convertToUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, RegisterDto.class);
    }
}
