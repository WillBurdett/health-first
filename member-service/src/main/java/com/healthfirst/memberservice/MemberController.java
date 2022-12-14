package com.healthfirst.memberservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("hello")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("hello/{name}")
    public String name(@PathVariable String name){
        return "hello world " + name;
    }
}
