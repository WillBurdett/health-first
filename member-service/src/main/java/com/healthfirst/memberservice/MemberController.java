package com.healthfirst.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("member")
    public Member helloMember(){
        return new Member("bob", "marley", 44, "MALE", "bob@gmail.com", "pass123", "OUTDOORS");
    }

    @GetMapping("hello/{name}")
    public String name(@PathVariable String name){
        return "hello world " + name;
    }

    @GetMapping("members")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
}
