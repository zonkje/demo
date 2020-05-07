package com.example.demo.auth.security.passwordencode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {

    protected final PasswordEncoder passwordEncoder;

    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @EncodedMapping
    public String encode(String password){
        return passwordEncoder.encode(password);
    }

}
