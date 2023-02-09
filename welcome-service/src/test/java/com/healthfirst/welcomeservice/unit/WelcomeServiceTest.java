package com.healthfirst.welcomeservice.unit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.healthfirst.welcomeservice.enums.Gender;
import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.feign.ClassesServiceCalls;
import com.healthfirst.welcomeservice.feign.EmailServiceCalls;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
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

  private final List<ClassInfo> ALL_CLASSES = List.of(
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
  public void getRelevantClasses_HappyPath() {
    // given
    List<Interest> interests = List.of(Interest.DANCE);
    when(classesServiceCalls.getRelevantClassesFromClassesService(interests)).thenReturn(List.of(ALL_CLASSES.get(0)));

    // when
    List<ClassInfo> actual = service.getRelevantClasses(interests);

    // then
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualTo(ALL_CLASSES.get(0));
    verify(classesServiceCalls, times(1)).getRelevantClassesFromClassesService(interests);
  }

  @Test
  public void handleNewMember_HappyPath() throws Exception {
    // given
    Member bob = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123", List.of(Interest.DANCE));
    List<ClassInfo> relevantClasses = List.of(ALL_CLASSES.get(0));
    when(classesServiceCalls.getRelevantClassesFromClassesService(bob.getInterests())).thenReturn(relevantClasses);

    // when
    service.handleNewMember(bob);

    // then
    verify(emailServiceCalls, times(1)).
        sendRelevantClassesToEmailService(
            bob.getFirstName(),
            bob.getEmail(),
            relevantClasses);
  }
}