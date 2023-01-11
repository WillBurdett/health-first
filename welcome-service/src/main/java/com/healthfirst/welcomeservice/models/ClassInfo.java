package com.healthfirst.welcomeservice.models;

import com.healthfirst.welcomeservice.enums.Interest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "classType cannot be empty")
    private Interest classType;
    @Future(message = "classTime must take place in the future")
    private LocalDateTime classTime;
}
