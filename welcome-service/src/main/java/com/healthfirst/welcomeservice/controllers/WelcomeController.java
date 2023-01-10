package com.healthfirst.welcomeservice.controllers;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import com.healthfirst.welcomeservice.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return service.getRelevantClasses(Interest.DANCE);
    }
    @PostMapping(
            value = "/welcome",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ClassInfo> addClass(@Valid @RequestBody Member member) {
        List<ClassInfo> addMemberClass = service.handleNewMember(member);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(addClass.getId())
//                .toUri();
        return  addMemberClass;
    }
}
