package com.healthfirst.memberservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("member-service")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Configuration {

  private String companyEmail;
}
