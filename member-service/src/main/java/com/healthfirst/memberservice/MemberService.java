package com.healthfirst.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MemberService {


    private MemberRepo memberRepo;
    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

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
        return memberRepo.findAll();
    }

    public Member getMemberById(Long id) {
       return memberList.stream().filter(member -> member.getId() == id).findFirst().get();
    }

    public void addMember(Member member) {
       memberRepo.save(member);
    }

    public void deleteMember(Long id) {
       memberList.removeIf(member -> member.getId() == id);
    }

    public void updateMember(Long id, Member member) {
       Member curr = memberList.stream().filter(m -> m.getId() == id).findFirst().get();
       curr.setFirstName(member.getFirstName());
       curr.setLastName(member.getLastName());
       curr.setAge(member.getAge());
       curr.setEmail(member.getEmail());
       curr.setGender(member.getGender());
       curr.setPassword(member.getPassword());
       curr.setInterest(member.getInterest());
    }
}
