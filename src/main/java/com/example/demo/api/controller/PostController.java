package com.example.demo.api.controller;

import com.example.demo.api.model.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/post")
@CrossOrigin(origins = "*")
public class PostController {

    // for test purposes
//    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    public String getPost(){ //for test purposes
        return "test";
    }

}
