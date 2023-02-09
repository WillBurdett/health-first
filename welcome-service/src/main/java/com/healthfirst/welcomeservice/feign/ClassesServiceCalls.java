package com.healthfirst.welcomeservice.feign;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient (value = "classes-service", url = "http://classes-service:8082")
public interface ClassesServiceCalls {

  @PostMapping(value = "/classes/relevant", produces = "application/json")
  List<ClassInfo> getRelevantClassesFromClassesService(@RequestBody List<Interest> classType);
}
