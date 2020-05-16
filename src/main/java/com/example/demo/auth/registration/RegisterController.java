package com.example.demo.auth.registration;

import com.example.demo.auth.user.UserDto;
import com.example.demo.auth.user.UserMapper;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public RegisterController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }
    /* JSON example for signup
    {
	  "username": "szyemkmeow",
	  "password": "passwordpassword",
	  "firstName": "Szymek",
	  "secondName": "Er",
	  "email": "test@gmail.com"
    }
    */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto signUp(@RequestBody @Valid UserSignUp newUser) {

        return userRepositoryService.signUpUser(newUser);

    }
}
