package com.learn.start.dto.response;

public class LoginDTO_Res {
    private String token;
    private String username;

    public LoginDTO_Res() {
    }

    public LoginDTO_Res(String token, String username) {
        this.token = token;
        this.username = username;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
