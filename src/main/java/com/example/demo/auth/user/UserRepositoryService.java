package com.example.demo.auth.user;

import com.example.demo.auth.registration.UserSignUp;
import com.example.demo.auth.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService implements UserDetailsService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryService(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public UserDto signUpUser(UserSignUp newUser){
        User user = userMapper.toUser(newUser);
        return userMapper.toUserDto(userRepo.save(user));
    }

}
