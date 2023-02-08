package com.healthfirst.emailservice.controllers;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.services.EmailService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.mail.MessagingException;
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

  @PostMapping(path = "/email/name/{name}/email/{email}")
  public void handleClassesToEmail(
      @RequestBody List<ClassInfo> classes,
      @PathVariable String name,
      @PathVariable String email) throws MessagingException, IOException {
    service.handleWelcomeEmailToNewMembers(classes, name, email);
  }
}
