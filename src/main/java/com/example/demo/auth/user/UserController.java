package com.example.demo.auth.user;

import com.example.demo.auth.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable("id") Long id) {
        Optional<User> optionalUserDto = userRepository.findById(id);
        return optionalUserDto
                .map(user -> new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/edit/{id}")
    public UserDto updateUser(@PathVariable("id") Long id,
                              @RequestBody UserDto userToUpdate) {
        User user = userRepository.findById(id).get();
        if (userToUpdate.getFirstName() != null) {
            user.setFirstName(userToUpdate.getFirstName());
        }
        if (userToUpdate.getSecondName() != null) {
            user.setSecondName(userToUpdate.getSecondName());
        }
        if (userToUpdate.getEmail() != null) {
            user.setEmail(userToUpdate.getEmail());
        }
        return userMapper.toUserDto(userRepository.save(user));
    }

}
