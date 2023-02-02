package com.healthfirst.emailservice.unit;

import static org.junit.Assert.assertFalse;

import com.healthfirst.emailservice.enums.ClassType;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.models.Email;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(Email.class)
public class EmailValidations {

  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void emptyName_CausesError(){
    Email Email = new Email("","bob@gmail.com", Arrays.asList(
        new ClassInfo(
            "Beginners Swimming",
            "David Schwimmer",
            ClassType.SWIMMING,
            LocalDateTime.of(2024, 2, 2, 2,2))));
    Set<ConstraintViolation<Email>> violations = validator.validate(Email);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void invalidEmail_CausesError(){
    Email Email = new Email("Bob","bobgmail.com", Arrays.asList(
        new ClassInfo(
        "Beginners Swimming",
        "David Schwimmer",
        ClassType.SWIMMING,
        LocalDateTime.of(2024, 2, 2, 2,2))));
    Set<ConstraintViolation<Email>> violations = validator.validate(Email);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void nullClassInfoList_CausesError(){
    Email Email = new Email("Bob","bobgmail.com", null);
    Set<ConstraintViolation<Email>> violations = validator.validate(Email);
    assertFalse(violations.isEmpty());
  }

}
