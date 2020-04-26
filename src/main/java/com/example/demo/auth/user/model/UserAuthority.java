package com.example.demo.auth.user.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public enum UserAuthority {

    POST_READ("post:read"),
    POST_WRITE("post:write"),
    FIXTURE_READ("fixture:read"),
    FIXTURE_WRITE("fixture:write");


    private final String authority;

    public String getAuthority(){
        return authority;
    }

}
