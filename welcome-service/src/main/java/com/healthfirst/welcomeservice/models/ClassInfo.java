package com.healthfirst.welcomeservice.models;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.validation.InterestTypeSubset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {

    @NotNull(message = "id must be present")
    private Long id;
    @NotBlank(message = "className cannot be empty")
    private String className;
    @NotBlank(message = "instructor cannot be empty")
    private String instructor;
    @NotNull(message = "classType cannot be null")
    @InterestTypeSubset(anyOf = {Interest.DANCE, Interest.TEAMSPORTS, Interest.SWIMMING, Interest.ATHLETICS})
    private Interest classType;
    @Future(message = "classTime must take place in the future")
    private LocalDateTime classTime;
}

