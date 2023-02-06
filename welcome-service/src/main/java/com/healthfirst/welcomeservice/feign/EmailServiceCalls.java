package com.healthfirst.welcomeservice.feign;

import com.healthfirst.welcomeservice.models.ClassInfo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "email-service", url = "http://email-service:8100")
public interface EmailServiceCalls {

  @PostMapping(value = "/email/name/{name}/email/{email}", produces = "application/json")
  List<ClassInfo> sendRelevantClassesToEmailService(
      @PathVariable String name,
      @PathVariable String email,
      @RequestBody List<ClassInfo> relevantClasses);
}
