package com.healthfirst.welcomeservice.unit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.feign.ClassesServiceCalls;
import com.healthfirst.welcomeservice.feign.EmailServiceCalls;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.services.WelcomeService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(WelcomeService.class)
public class WelcomeServiceTest {

  @Autowired
  WelcomeService service;

  @MockBean
  ClassesServiceCalls classesServiceCalls;

  @MockBean
  EmailServiceCalls emailServiceCalls;

  private List<ClassInfo> allClasses = List.of(
      new ClassInfo(
          1L, "Rhythmic Aerobics",
          "Mr.Tickles", Interest.DANCE,
          LocalDateTime.of(2023, 2,
              2,
              14,
              0)
      ),
      new ClassInfo(
          2L, "Amateur 5-a-side",
          "Mr.Beckham", Interest.TEAMSPORTS,
          LocalDateTime.of(2023, 3,
              3,
              15,
              0)
      ));

  @Test
  public void getRelevantClasses_HappyPath(){
    // given
    Interest interest = Interest.DANCE;
    when(classesServiceCalls.getRelevantClassesFromClassesService(interest)).thenReturn(List.of(allClasses.get(0)));

    // when
    List<ClassInfo> actual = service.getRelevantClasses(interest);

    // then
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualTo(allClasses.get(0));
    verify(classesServiceCalls, times(1)).getRelevantClassesFromClassesService(interest);
  }
}