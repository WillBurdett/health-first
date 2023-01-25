package com.healthfirst.memberservice.feign;

import com.healthfirst.memberservice.models.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient (value = "welcome-service", url = "http://welcome-service:8888")
public interface WelcomeServiceCalls {

  @PostMapping(value = "/welcome")
  void sendNewMemberToWelcomeService(@RequestBody Member member);
}
