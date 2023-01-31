package com.healthfirst.emailservice.services;

import com.healthfirst.emailservice.models.ClassInfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  public List<ClassInfo> handleClassesToEmail(List<ClassInfo> classes) {
    return classes;
  }

}
