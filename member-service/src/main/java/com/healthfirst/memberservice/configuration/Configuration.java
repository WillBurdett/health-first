package com.healthfirst.memberservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("member-service")
public class Configuration {

  private String companyEmail;
}
