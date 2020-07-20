package com.example.demo.auth.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



@Data
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;

}
