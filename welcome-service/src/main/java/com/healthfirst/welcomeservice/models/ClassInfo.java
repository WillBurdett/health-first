package com.healthfirst.welcomeservice.models;

import com.healthfirst.welcomeservice.enums.Interest;
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
    @NotNull(message = "classType cannot be empty")
    private Interest classType;
    @Future(message = "classTime must take place in the future")
    private LocalDateTime classTime;
}

// TODO: 17/01/2023 => changed the @NotBlank to @NotNull in the classtype because its an enum -
//  basically @NotBlank, @NotEmpty is for validating String They cannot be used for an enum but No errors
//  if we use @NotNull annotation this is not the desired behavior because it allows for blank fields
//  - (will look it into more)
