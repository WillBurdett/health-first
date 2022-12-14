package com.healthfirst.memberservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MemberService {

  private static List <Member> memberList = new ArrayList<>();

   static{
       memberList.add(new Member(1L,"bob", "marley", 44, "MALE", "bob@gmail.com",
               "pass123", "OUTDOORS"));
       memberList.add(new Member(2L, "ali", "marley", 30, "MALE", "ali@gmail.com",
               "pass123", "OUTDOORS"));
       memberList.add(new Member(3L, "sara", "will", 44, "FEMALE", "sara@gmail.com",
               "pass123", "INDOORS"));
       }

    public List<Member> getAllMembers(){
        return memberList;
    }

    public Member getMemberById(Long id) {
       return memberList.stream().filter(member -> member.getId() == id).findFirst().get();
    }

    public void addMember(Member member) {
       memberList.add(member);
    }
}
