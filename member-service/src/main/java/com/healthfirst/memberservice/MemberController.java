package com.healthfirst.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("members/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }
    @PostMapping("members")
    public void addMember(@RequestBody Member member){
        memberService.addMember(member);
    }
}
