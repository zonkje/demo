package com.example.demo.auth.user;

import com.example.demo.auth.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable("id") Long id) {
        Optional<User> optionalUserDto = userRepository.findById(id);
        return optionalUserDto
                .map(user -> new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/edit")
    public UserDto updateUser(Authentication authentication,
                              @RequestBody UserDto userToUpdate) {
        User user = userRepository.findByUsername(authentication.getName()).get();
        if (userToUpdate.getFirstName() != null) {
            user.setFirstName(userToUpdate.getFirstName());
        }
        if (userToUpdate.getLastName() != null) {
            user.setLastName(userToUpdate.getLastName());
        }
        if (userToUpdate.getEmail() != null) {
            user.setEmail(userToUpdate.getEmail());
        }
        return userMapper.toUserDto(userRepository.save(user));
    }

}
