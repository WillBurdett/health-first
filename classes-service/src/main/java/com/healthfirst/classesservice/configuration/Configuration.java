package com.healthfirst.classesservice.configuration;


import com.healthfirst.classesservice.enums.ClassType;
import com.healthfirst.classesservice.models.ClassInfo;
import com.healthfirst.classesservice.repositories.ClassesRepo;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;


@org.springframework.context.annotation.Configuration
public class Configuration {
    public class ValidationAndHydration {
        @Bean
        CommandLineRunner commandLineRunner(ClassesRepo classesRepo) {
            return args -> {
                List<ClassInfo> hydrationClasses = List.of(
                        new ClassInfo(
                                "Beginners Swimming",
                                "David Schwimmer",
                                ClassType.SWIMMING,
                                LocalDateTime.of(2024, 2, 2, 2, 2))
                        ,
                        new ClassInfo(
                                "7-a-side",
                                "Lionel Messi",
                                ClassType.TEAMSPORTS,
                                LocalDateTime.of(2024, 2, 2, 2, 2))
                        ,
                        new ClassInfo(
                                "10km group run",
                                "Paula Radcliffe",
                                ClassType.ATHLETICS,
                                LocalDateTime.of(2024, 2, 2, 2, 2))
                );
                classesRepo.saveAll(hydrationClasses);
            };
        }
    }
}
