package com.healthfirst.welcomeservice.services;

import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WelcomeService {

    public static List<ClassInfo> allClasses = new ArrayList<>();
    static {
        allClasses.add(
                new ClassInfo(
                        1L, "Rhythmic Aerobics",
                        "Mr.Tickles", Interest.DANCE,
                        LocalDateTime.of(2023, 2,
                                2,
                                14,
                                0)
                )
        );
        allClasses.add(
                new ClassInfo(
                        2L, "Amateur 5-a-side",
                        "Mr.Beckham", Interest.TEAMSPORTS,
                        LocalDateTime.of(2023, 3,
                                3,
                                15,
                                0)
                )
        );
        allClasses.add(
                new ClassInfo(
                        3L, "Aqua-fit",
                        "Mrs.Doubtfire", Interest.SWIMMING,
                        LocalDateTime.of(2022, 4,
                                4,
                                16,
                                0)
                )
        );
    }

    public List<ClassInfo> getRelevantClasses(Interest interest){
        List <ClassInfo> relevantClasses = new ArrayList<>();
        // TODO: 16/01/2023 GET method for all classes from class-service

        // TODO: 10/01/2023 this will be to handle List <Interest>
//        for (Interest i:
//             interests) {
//            for (ClassInfo c:
//                 allClasses) {
//                if (i.equals(c.getClassType())){
//                    relevantClasses.add(c);
//                }
//            }
//        }

        // filtering all classes to match Members Interest
        for (ClassInfo c:
            allClasses) {
            if (interest.equals(c.getClassType())){
                    relevantClasses.add(c);
                }
            }

        return relevantClasses;
    }

    public List<ClassInfo> handleNewMember(Member member){
        System.out.println(member);
        // TODO: 16/01/2023 email relevant classes
        return getRelevantClasses(member.getInterest());
    }
}
