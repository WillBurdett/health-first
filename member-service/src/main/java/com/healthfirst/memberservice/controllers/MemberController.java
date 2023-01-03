package com.healthfirst.memberservice.controllers;

import com.healthfirst.memberservice.models.Member;
import com.healthfirst.memberservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("members/{id}")
    public Member getMemberById(@PathVariable Long id)  {
      return memberService.getMemberById(id);
    }
    @PostMapping(
            value = "members",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    // members/4 => /members/{id}, members.getID => 201 error code
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member){
        Member savedMember = memberService.addMember(member);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("members/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }
    @PutMapping("members/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody Member member){
        memberService.updateMember(id, member);
    }
}
