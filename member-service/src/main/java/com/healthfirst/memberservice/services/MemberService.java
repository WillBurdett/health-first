package com.healthfirst.memberservice.services;

import com.healthfirst.memberservice.exceptions.MemberWithEmailAlreadyExists;
import com.healthfirst.memberservice.feign.WelcomeServiceCalls;
import com.healthfirst.memberservice.models.Member;
import com.healthfirst.memberservice.exceptions.MemberNotFoundException;
import com.healthfirst.memberservice.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepo memberRepo;

    private final WelcomeServiceCalls welcomeServiceCalls;

    @Autowired
    public MemberService(MemberRepo memberRepo, WelcomeServiceCalls welcomeServiceCalls) {
        this.memberRepo = memberRepo;
        this.welcomeServiceCalls = welcomeServiceCalls;
    }

    public List<Member> getAllMembers(){
        return memberRepo.findAll();
    }

    public Member getMemberById(Long id) {
        Optional<Member> member=  memberRepo.findAll().stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        if(member.isPresent() ){
            return member.get();
        }
        throw new MemberNotFoundException("Member not found with id " + id);
    }

    public Member addMember(Member member) {
        Optional<Member> optionalMember =
            memberRepo.findAll().stream().findFirst().filter(m -> m.getEmail().equals(member.getEmail()));
        if (optionalMember.isPresent()){
            throw new MemberWithEmailAlreadyExists("member with the email " + member.getEmail() + " already exists");
        }
        Member savedMember = memberRepo.save(member);
        // TODO: 16/01/2023 send Member to welcome-service via POST method
        welcomeServiceCalls.sendNewMemberToWelcomeService(member);
        return savedMember;
    }

    public void deleteMember(Long id) {
       memberRepo.deleteById(id);
    }

    public Member updateMember(Long id, Member member) {
       Optional<Member> curr = memberRepo.findAll().stream().filter(m -> m.getId() == id).findFirst();
       if (curr.isPresent()){
           curr.get().setFirstName(member.getFirstName());
           curr.get().setLastName(member.getLastName());
           curr.get().setAge(member.getAge());
           curr.get().setEmail(member.getEmail());
           curr.get().setGender(member.getGender());
           curr.get().setPassword(member.getPassword());
           curr.get().setInterests(member.getInterests());
           memberRepo.save(curr.get());
           return curr.get();
       }
        throw new MemberNotFoundException("Member not found with id " + id);
    }
}
