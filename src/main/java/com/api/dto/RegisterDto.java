package com.api.dto;

import java.util.Objects;

public class RegisterDto {

    private String name;
    private String username;
    private String email;
    private String password;

    public RegisterDto(){}

    public RegisterDto(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,username,email,password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegisterDto myClass = (RegisterDto) obj;
        return Objects.equals(name, myClass.name) &&
                Objects.equals(username, myClass.username) &&
                Objects.equals(email, myClass.email) &&
                Objects.equals(password, myClass.password);
    }

    @Override
    public String toString() {
        return name+", "+username+", "+email+", "+password;
    }
}