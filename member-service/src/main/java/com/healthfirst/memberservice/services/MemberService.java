package com.healthfirst.memberservice.services;

import com.healthfirst.memberservice.models.Member;
import com.healthfirst.memberservice.exceptions.MemberNotFoundException;
import com.healthfirst.memberservice.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepo memberRepo;
    @Autowired
    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
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
        Member savedMember = memberRepo.save(member);
        return savedMember;
    }

    public void deleteMember(Long id) {
       memberRepo.deleteById(id);
    }

    public void updateMember(Long id, Member member) {
       Member curr = memberRepo.findAll().stream().filter(m -> m.getId() == id).findFirst().get();
       curr.setFirstName(member.getFirstName());
       curr.setLastName(member.getLastName());
       curr.setAge(member.getAge());
       curr.setEmail(member.getEmail());
       curr.setGender(member.getGender());
       curr.setPassword(member.getPassword());
       curr.setInterest(member.getInterest());
       memberRepo.save(curr);
    }
}
