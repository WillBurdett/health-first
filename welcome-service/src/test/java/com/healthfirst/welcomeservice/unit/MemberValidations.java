package com.healthfirst.welcomeservice.unit;

import static org.junit.Assert.assertFalse;

import com.healthfirst.welcomeservice.controllers.WelcomeController;
import com.healthfirst.welcomeservice.enums.Gender;
import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.Member;
import java.util.List;
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
  public void firstName_LeftBlankCausesError(){
    Member member = new Member(1L, "", "marley", 21, Gender.MALE, "bob@gmail.com", "pass1234",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void secondName_LeftBlankCausesError(){
    Member member = new Member(1L, "bob", "", 21, Gender.MALE, "bob@gmail.com", "pass1234",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void age_LessThan18CausesError(){
    Member member = new Member(1L, "bob", "marley", 5, Gender.MALE, "bob@gmail.com", "pass1234",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void age_MoreThan130CausesError(){
    Member member = new Member(1L, "bob", "marley", 131, Gender.MALE, "bob@gmail.com", "pass1234",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void email_InvalidEmailCausesError(){
    Member member = new Member(1L, "bob", "marley", 21, Gender.MALE, "gmail.com", "pass1234",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void password_PasswordLessThan8CharactersCausesError(){
    Member member = new Member(1L, "bob", "marley", 21, Gender.MALE, "gmail.com", "pass",
        List.of(Interest.DANCE));
    Set<ConstraintViolation<Member>> violations = validator.validate(member);
    assertFalse(violations.isEmpty());
  }

}
