package com.healthfirst.welcomeservice.controllers;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WelcomeServiceController {

    @GetMapping("/welcome")
    public ClassInfo hello(){
        return new ClassInfo(1L, "Rhythmic Aerobics", "Mr.Tickles", Interest.DANCE, LocalDateTime.of(2023, 2, 2, 2,0));
    }
}
