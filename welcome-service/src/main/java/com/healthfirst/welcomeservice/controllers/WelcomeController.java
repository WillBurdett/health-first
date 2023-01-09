package com.healthfirst.welcomeservice.controllers;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import com.healthfirst.welcomeservice.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @PostMapping(
            value = "/classInfo/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClassInfo> addClass(@Valid @RequestBody ClassInfo classInfo) {
        ClassInfo addClass = service.getRelevantClasses(List.of(Interest));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addClass.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
