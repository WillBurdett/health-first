package com.healthfirst.memberservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("member")
    public Member helloMember(){
        return new Member("bob", "marley", 44, "MALE", "bob@gmail.com", "pass123", "OUTDOORS");
    }

    @GetMapping("hello/{name}")
    public String name(@PathVariable String name){
        return "hello world " + name;
    }
}
