package com.api.dto;

public class JwtAuthResponse {
    private String accessToken;
    private String tokenType="Bearer ";
    private String username;

    public JwtAuthResponse(){}

    public JwtAuthResponse(String accessToken, String tokenType,String username) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.username=username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}