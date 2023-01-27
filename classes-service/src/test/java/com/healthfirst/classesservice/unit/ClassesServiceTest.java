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
  public void getAllClasses_HappyPath() {
    // given
    ClassInfo classInfo =  new ClassInfo(
        1L,
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
  public void getClassById_HappyPath() {
    // given
    ClassInfo classInfo =  new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));
    when(repo.findAll()).thenReturn(Arrays.asList(classInfo));

    // when
    ClassInfo actual = service.getClassById(1L);

    // then
    ClassInfo expected = classInfo;
    verify(repo, times(1)).findAll();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void addClass_HappyPath() {
    // given
    ClassInfo classInfo =  new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));

    // when
    service.addClass(classInfo);

    // then
    verify(repo, times(1)).save(classInfo);
  }

  @Test
  public void deleteClass_HappyPath() {
    // given
    Long id = 1L;

    // when
    service.deleteClass(id);

    // then
    verify(repo, times(1)).deleteById(id);
  }

  @Test
  public void updateClass_HappyPath() {
    // given
    ClassInfo originalClassInfo =  new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));
    when(repo.findAll()).thenReturn(List.of(originalClassInfo));

    ClassInfo updatedClassInfo =  new ClassInfo(
        1L,
        "Advanced Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));

    // when
    service.updateClass(1L, updatedClassInfo);

    // then
    verify(repo, times(1)).findAll();
    verify(repo, times(1)).save(updatedClassInfo);
  }

  @Test
  public void getRelevantClasses_HappyPath() {
    // given
    ClassInfo classInfo =  new ClassInfo(
        1L,
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2));
    when(repo.findAll()).thenReturn(Arrays.asList(classInfo));

    // when
    List<ClassInfo> actual = service.getRelevantClasses(ClassType.SWIMMING);
    List<ClassInfo> expected = Arrays.asList(classInfo);

    // then
    verify(repo, times(1)).findAll();
    assertThat(actual).isEqualTo(expected);
  }
}