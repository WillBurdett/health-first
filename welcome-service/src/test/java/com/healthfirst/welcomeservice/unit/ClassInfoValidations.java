package com.healthfirst.welcomeservice.unit;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertFalse;




@RunWith(SpringRunner.class)
@WebMvcTest(ClassInfo.class)
public class ClassInfoValidations {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     @Test
    public void className_LeftBlankCausesError(){
        ClassInfo classInfo = new ClassInfo(1l, "", "James",
                Interest.DANCE,
                LocalDateTime.of(2023,2, 10, 12, 10));

         Set<ConstraintViolation<ClassInfo>> constraintViolations = validator.validate(classInfo);
         assertFalse(constraintViolations.isEmpty());

     }

    @Test
    public void  instructor_LeftBlankCausesError(){
        ClassInfo classInfo = new ClassInfo(1l, "Boxing", "",
                Interest.TEAMSPORTS,
                LocalDateTime.of(2023,2, 10, 12, 10));

        Set<ConstraintViolation<ClassInfo>> constraintViolations = validator.validate(classInfo);
        assertFalse(constraintViolations.isEmpty());

    }

    @Test
    public void  classTypeName_NullCausesError(){
        ClassInfo classInfo = new ClassInfo(1l, "Boxing", "james", null,
            LocalDateTime.of(2023,2, 10, 12, 10));

        Set<ConstraintViolation<ClassInfo>> constraintViolations = validator.validate(classInfo);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    public void  classTimeError_Past(){
        ClassInfo classInfo = new ClassInfo(1l, "Boxing", "james",
                Interest.TEAMSPORTS,
                LocalDateTime.of(2022,2, 10, 12, 10));

        Set<ConstraintViolation<ClassInfo>> constraintViolations = validator.validate(classInfo);
        assertFalse(constraintViolations.isEmpty());

    }





}
