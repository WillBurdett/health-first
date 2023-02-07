package com.healthfirst.welcomeservice.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.healthfirst.welcomeservice.controllers.WelcomeController;
import com.healthfirst.welcomeservice.enums.Gender;
import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import com.healthfirst.welcomeservice.services.WelcomeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
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
@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {

  private  static final String CLASS_TIME = "2023-02-02T14:00:00";
  private static final Member MEMBER = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass1234",
      Interest.DANCE);
  private static final Member INVALID_MEMBER = new Member(1L, "", "marley", 21, Gender.MALE, "bob@gmail.com",
      "pass1234",
      Interest.DANCE);
  private static final List<ClassInfo> CLASS_INFO_LIST = List.of(
      new ClassInfo(
        1L, "Rhythmic Aerobics",
        "Mr.Tickles", Interest.DANCE,
        LocalDateTime.parse(CLASS_TIME))
    );

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WelcomeService service;

  @Test
  public void handleNewMember_HappyPath() throws Exception {

    when(service.handleNewMember(MEMBER)).thenReturn(CLASS_INFO_LIST);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(MEMBER);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/welcome")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].className", is("Rhythmic Aerobics")))
        .andExpect(jsonPath("$[0].instructor", is("Mr.Tickles")))
        .andExpect(jsonPath("$[0].classType", is(Interest.DANCE.toString())))
        .andExpect(jsonPath("$[0].classTime", is(CLASS_TIME)));

    verify(service, times(1)).handleNewMember(MEMBER);
  }

  @Test
  public void handleNewMember_ErrorsIfMemberInvalid() throws Exception {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(INVALID_MEMBER);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/welcome")
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isBadRequest());
  }
}