package com.healthfirst.welcomeservice.controllers;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WelcomeController {

    @Autowired
    private final WelcomeService service;

    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/welcome/classes")
    public List<ClassInfo> hello(){
        return service.getRelevantClasses(List.of(Interest.DANCE));
    }
}
