package com.healthfirst.classesservice.unit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.healthfirst.classesservice.enums.ClassType;
import com.healthfirst.classesservice.models.ClassInfo;
import com.healthfirst.classesservice.repositories.ClassesRepo;
import com.healthfirst.classesservice.services.ClassesService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(ClassesService.class)
public class ClassesServiceTest {

  @Autowired
  ClassesService service;

  @MockBean
  ClassesRepo repo;

  @Test
  public void getAllClasses() {
    // given
    ClassInfo classInfo =  new ClassInfo(
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));

    when(repo.findAll()).thenReturn(Arrays.asList(classInfo));
    List<ClassInfo> expected = Arrays.asList(classInfo);

    // when
    List<ClassInfo> actual = service.getAllClasses();

    // then
    verify(repo, times(1)).findAll();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void getClassById() {
  }

  @Test
  public void addClass() {
  }

  @Test
  public void deleteClass() {
  }

  @Test
  public void updateClass() {
  }

  @Test
  public void getRelevantClasses() {
  }
}