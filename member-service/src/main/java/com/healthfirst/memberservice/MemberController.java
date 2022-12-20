package com.healthfirst.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;
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
    @PostMapping("members")
    // members/4 => /members/{id}, members.getID => 201 error code
    public ResponseEntity<Member> addMember(@RequestBody Member member){
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
