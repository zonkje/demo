package com.example.demo.auth.controller;

import com.example.demo.auth.user.UserDto;
import com.example.demo.auth.user.UserMapper;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.model.User;
import com.example.demo.auth.user.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
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

        userRepository.save(userMapper.toUser(userDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable("id") Long id){
        Optional<User> optionalUserDto = userRepository.findById(id);
        return optionalUserDto
                .map(user -> new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
