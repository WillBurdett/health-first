package com.healthfirst.emailservice.unit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.healthfirst.emailservice.controllers.EmailController;
import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.models.Email;
import com.healthfirst.emailservice.services.EmailService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailController.class)
public class EmailControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  EmailService service;

  private final ClassInfo CLASSINFO =  new ClassInfo(
      1L,
      "Beginners Swimming",
      "David Schwimmer",
      ClassType.SWIMMING,
      LocalDateTime.of(2024, 2, 2, 2,2));
  private static final String MEMBER_NAME = "Bob";
  private static final String MEMBER_EMAIL = "bob@gmail.com";
  private static final String HEALTH_FIRST_EMAIL = "health.first.app.v1@gmail.com";
  private static final String WELCOME_SUBJECT = "Welcome to Health First!";

  @Test
  public void handleClassesToEmail() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();

    String relevantClasses = ow.writeValueAsString(List.of(CLASSINFO));

    mockMvc.perform(MockMvcRequestBuilders.post("/email/name/"+ MEMBER_NAME + "/email/" + MEMBER_EMAIL)
        .contentType(MediaType.APPLICATION_JSON).content(relevantClasses));
    verify(service, times(1)).
        handleWelcomeEmailToNewMembers(
            List.of(CLASSINFO),
            MEMBER_NAME,
            MEMBER_EMAIL);
  }
}