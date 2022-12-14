package com.healthfirst.memberservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private String password;
    private String interest;
}
