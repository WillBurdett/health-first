package com.healthfirst.classesservice.models;

import com.healthfirst.classesservice.enums.ClassType;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {

    @Id
    @NotNull(message = "id must be present")
    private Long id;
    @NotBlank(message = "className cannot be empty")
    private String className;
    @NotBlank(message = "instructor cannot be empty")
    private String instructor;
    @NotNull(message = "classType cannot be null")
    // TODO: 18/01/2023 this will come when custom constraint validations are added 
    //@ClassTypeTypeSubset(anyOf = {ClassType.DANCE, ClassType.TEAMSPORTS, ClassType.SWIMMING, ClassType.ATHLETICS})
    private ClassType classType;
    @Future(message = "classTime must take place in the future")
    private LocalDateTime classTime;
}

