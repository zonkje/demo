package com.example.demo.auth.jwt;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;
    private String password;

}
