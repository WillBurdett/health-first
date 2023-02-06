package com.healthfirst.welcomeservice.services;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.feign.ClassesServiceCalls;
import com.healthfirst.welcomeservice.feign.EmailServiceCalls;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelcomeService {

    private final ClassesServiceCalls classesServiceCalls;
    private final EmailServiceCalls emailServiceCalls;
    private static final String HEALTH_FIRST_EMAIL = "health.first.app.v1@gmail.com";
  //  private final Gmail gmailService;

    @Autowired
    public WelcomeService(ClassesServiceCalls classesServiceCalls, EmailServiceCalls emailServiceCalls) throws Exception {
        this.classesServiceCalls = classesServiceCalls;
        this.emailServiceCalls = emailServiceCalls;
    }

    public List<ClassInfo> handleNewMember(Member member) throws Exception {
        System.out.println(member);
        List<ClassInfo> relevantClasses;
        try {
            relevantClasses = getRelevantClasses(member.getInterest());
        } catch (Exception e){
            System.out.println(e.getMessage()+ "\n" + e.getCause() + "\n" + e.getLocalizedMessage());
            throw new Exception(e.getMessage());
        }

        emailServiceCalls.sendRelevantClassesToEmailService(member.getFirstName(), member.getEmail(), relevantClasses);

        return relevantClasses;
    }

    public List<ClassInfo> getRelevantClasses(Interest interest){
        // GETs all relevant classes from class-service based on singular interest
       List <ClassInfo> relevantClasses = classesServiceCalls.getRelevantClassesFromClassesService(interest);
        return relevantClasses;
    }

}
