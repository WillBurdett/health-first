package com.healthfirst.emailservice.controllers;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

  @GetMapping(path = "/hello")
  public ClassInfo helloWorld(){
    return new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));
  }
}
