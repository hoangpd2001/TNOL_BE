package com.learn.start.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO_Req {
    @NotBlank(message = "Tài Khoản Không được để trống")
    private String email;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    public LoginDTO_Req() {
    }

    public LoginDTO_Req(String email, String password) {
        this.email = email;
        this.password = password;
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
}
