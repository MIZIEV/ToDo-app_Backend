package com.api.dto;

import java.util.Objects;

public class LoginDto {

    private String usernameOrEmail;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameOrEmail, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LoginDto myClass = (LoginDto) obj;
        return Objects.equals(usernameOrEmail, myClass.usernameOrEmail) && Objects.equals(password, myClass.password);
    }

    @Override
    public String toString() {
        return usernameOrEmail + " " + password;
    }
}