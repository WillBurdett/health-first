package com.healthfirst.welcomeservice.feign;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (value = "classes-service", url = "http://localhost:8082/classes")
public interface ClassesServiceCalls {

  @GetMapping(value = "/relevant/{classType}", produces = "application/json")
  List<ClassInfo> getRelevantClassesFromClassesService(@PathVariable("classType") Interest interest);
}
