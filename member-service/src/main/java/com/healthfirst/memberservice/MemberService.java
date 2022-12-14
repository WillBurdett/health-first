package com.healthfirst.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
       return memberRepo.findAll().stream().filter(member -> member.getId() == id).findFirst().get();
    }

    public void addMember(Member member) {
       memberRepo.save(member);
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
    }
}
