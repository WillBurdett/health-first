package com.healthfirst.emailservice.services;

import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.models.Email;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  public List<ClassInfo> handleClassesToEmail(List<ClassInfo> classes, String name, String email) {
    System.out.println(new Email(name, email, classes).formatEmail());
    return classes;
  }

}
