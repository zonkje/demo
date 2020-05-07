package com.example.demo.auth.registration;

import com.example.demo.auth.user.UserMapper;
import com.example.demo.auth.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    public void signUp(@RequestBody @Valid UserSignUp newUser) {

        userRepository.save(userMapper.toUser(newUser));

    }
}
