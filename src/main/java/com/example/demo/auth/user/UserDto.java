package com.example.demo.auth.user;

import com.example.demo.auth.user.model.User;
import com.example.demo.auth.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;


@Data
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @Email
    @NotBlank
    private String email;

}
