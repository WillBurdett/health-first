package com.healthfirst.welcomeservice.controllers;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import com.healthfirst.welcomeservice.services.WelcomeService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.mail.MessagingException;
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

    @PostMapping(value = "/welcome")
    public List<ClassInfo> handleNewMember(@Valid @RequestBody Member member)
        throws MessagingException, GeneralSecurityException, IOException {
        List<ClassInfo> addMemberClass = service.handleNewMember(member);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(addClass.getId())
//                .toUri();
        return  addMemberClass;
    }
}
