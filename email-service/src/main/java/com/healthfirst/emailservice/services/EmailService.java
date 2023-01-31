package com.healthfirst.emailservice.services;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  public List<ClassInfo> handleClassesToEmail(List<ClassInfo> classes) {
    return classes;
  }

}
