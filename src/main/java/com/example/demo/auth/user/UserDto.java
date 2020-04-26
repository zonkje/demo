package com.example.demo.auth.user;

import com.example.demo.auth.user.model.User;
import com.example.demo.auth.user.model.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    /*
    * WHAT IS THE RIGHT WAY TO VALIDATE FIELDS? IN MODEL OR IN DTO
    * */

    @NotNull(message = "Username is required")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;
    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @Email
    @NotBlank
    private String email;

}
