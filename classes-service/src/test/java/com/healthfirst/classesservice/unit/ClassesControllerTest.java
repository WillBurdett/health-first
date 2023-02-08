package com.healthfirst.classesservice.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.healthfirst.classesservice.controllers.ClassesController;
import com.healthfirst.classesservice.enums.ClassType;
import com.healthfirst.classesservice.models.ClassInfo;
import com.healthfirst.classesservice.services.ClassesService;
import com.healthfirst.classesservice.utils.JsonUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
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
@WebMvcTest(ClassesController.class)
public class ClassesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ClassesService service;

  private final ClassInfo CLASSINFO =  new ClassInfo(
        1L,
            "Beginners Swimming",
            "David Schwimmer",
      ClassType.SWIMMING,
      LocalDateTime.of(2024, 2, 2, 2,2));

  @Test
  public void getAllClasses() throws Exception {
    when(service.getAllClasses()).thenReturn(Arrays.asList(CLASSINFO));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/classes")
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].className", is("Beginners Swimming")))
        .andExpect(jsonPath("$[0].instructor", is("David Schwimmer")))
        .andExpect(jsonPath("$[0].classType", is(ClassType.SWIMMING.toString())))
        .andExpect(jsonPath("$[0].classTime", is("2024-02-02T02:02:00")));

    verify(service, times(1)).getAllClasses();
  }

  @Test
  public void getClassById() throws Exception {
    when(service.getClassById(1L)).thenReturn(CLASSINFO);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/classes/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.className", is("Beginners Swimming")))
        .andExpect(jsonPath("$.instructor", is("David Schwimmer")))
        .andExpect(jsonPath("$.classType", is(ClassType.SWIMMING.toString())))
        .andExpect(jsonPath("$.classTime", is("2024-02-02T02:02:00")));

    verify(service, times(1)).getClassById(1L);
  }

  @Test
  public void getRelevantClasses() {


  }

  @Test
  public void addClass() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();

    String newClass = ow.writeValueAsString(CLASSINFO);

    mockMvc.perform(MockMvcRequestBuilders.post("/classes")
        .contentType(MediaType.APPLICATION_JSON).content(newClass));
    verify(service, times(1)).addClass(CLASSINFO);
  }

  @Test
  public void deleteClass() {
  }

  @Test
  public void updateClass() {
  }

  @Test
  public void hydrate() {
  }
}