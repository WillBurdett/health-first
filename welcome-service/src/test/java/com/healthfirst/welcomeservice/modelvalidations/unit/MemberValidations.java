package com.healthfirst.welcomeservice.modelvalidations.unit;

import static org.junit.Assert.assertFalse;

import com.healthfirst.welcomeservice.controllers.WelcomeController;
import com.healthfirst.welcomeservice.enums.Gender;
import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.Member;
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
@WebMvcTest(Member.class)
public class MemberValidations {

  private Validator validator;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void firstName_LeftBlank(){
    Member blankFirstName = new Member(1L, "", "marley", 21, Gender.MALE, "bob@gmail.com", "pass1234",
        Interest.DANCE);
    Set<ConstraintViolation<Member>> violations = validator.validate(blankFirstName);
    assertFalse(violations.isEmpty());
  }

}
