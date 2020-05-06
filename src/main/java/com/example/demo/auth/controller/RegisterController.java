package com.example.demo.auth.controller;

import com.example.demo.auth.user.UserDto;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.model.User;
import com.example.demo.auth.user.model.UserRole;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
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
    public void signUp(@RequestBody @Valid UserDto userDto) {

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setSecondName(userDto.getSecondName());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole(UserRole.USER);
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        //var userEntity = mapper.map(userDto);
        userRepository.save(newUser);
    }

}
