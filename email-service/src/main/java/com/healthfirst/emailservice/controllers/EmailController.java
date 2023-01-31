package com.healthfirst.emailservice.controllers;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.services.EmailService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired
  private final EmailService service;

  public EmailController(EmailService service) {
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

  @PostMapping(path = "/email/name/{name}/email/{email}")
  public List<ClassInfo> handleClassesToEmail(
      @RequestBody List<ClassInfo> classes,
      @PathVariable String name,
      @PathVariable String email){
    return service.handleClassesToEmail(classes, name, email);
  }
}
