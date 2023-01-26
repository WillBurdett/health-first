package com.healthfirst.emailservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

  @GetMapping(path = "/hello")
  public String helloWorld(){
    return "Hello World!";
  }
}
