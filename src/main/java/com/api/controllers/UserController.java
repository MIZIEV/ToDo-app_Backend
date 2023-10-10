package com.api.controllers;


import com.api.dto.RegisterDto;
import com.api.model.User;
import com.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/update/{username}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody RegisterDto userDto, @PathVariable String username) {
        userService.updateUser(convertToUser(userDto), username);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private User convertToUser(RegisterDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    private RegisterDto convertToUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, RegisterDto.class);
    }
}