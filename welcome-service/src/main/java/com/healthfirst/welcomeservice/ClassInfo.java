package com.healthfirst.welcomeservice;

import com.healthfirst.welcomeservice.enums.Interest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {

    private Long id;
    private String className;
    private String instructor;
    private Interest classType;
    private LocalDateTime localDateTime;
}
