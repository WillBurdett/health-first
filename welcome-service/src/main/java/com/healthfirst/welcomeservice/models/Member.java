package com.healthfirst.welcomeservice.models;

import com.healthfirst.welcomeservice.enums.Gender;
import com.healthfirst.welcomeservice.enums.Interest;

import lombok.*;


import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    @Min(value = 18, message = "age must be 18 or higher")
    @Max(value = 130, message = "age cannot be more than 130")
    private Integer age;
    private Gender gender;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    private Interest interest;
}


