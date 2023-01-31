package com.healthfirst.emailservice.controllers;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.services.EmailService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

  @Autowired
  private final EmailService service;

  public EmailServiceController(EmailService service) {
    this.service = service;
  }

  @GetMapping(path = "/hello")
  public ClassInfo helloWorld(){
    return new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));
  }

  @PostMapping(path = "/email")
  public List<ClassInfo> handleClassesToEmail(@RequestBody List<ClassInfo> classes){
    return service.handleClassesToEmail(classes);
  }
}
