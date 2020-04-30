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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    * This method shouldn't required any authorization, so @PreAuthorize("permitAll()") ????
    * */

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid UserDto userDto){
        //tutaj jakas obsluga bledow


        // jest gdzies problem z castowaniem
        // przy próbie wysłania
        /*
        {
	        "username": "szyemkmeow",
	        "password": "passwordpassword",
	        "firstName": "Szymek",
	        "secondName": "Er",
	        "email": "test@gmail.com"
        }
        * */
        // na ten endpoint
        // class org.springframework.security.core.authority.SimpleGrantedAuthority cannot be cast to class java.lang.Enum
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setSecondName(userDto.getSecondName());
        newUser.setGrantedAuthorities(UserRole.USER.getGrantedAuthorities());
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        //var userEntity = mapper.map(userDto);
        userRepository.save(newUser);

        // - do bazy danych chce zapisac Usera, nie UserDto (chce miec (powinienem?) metadane odnosnie konta, czy zablkowane itd)
        // - co zatem powinienem zwrócić?

        //return null;
    }

}
