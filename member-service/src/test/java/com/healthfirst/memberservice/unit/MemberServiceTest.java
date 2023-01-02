package com.healthfirst.memberservice.unit;

import com.healthfirst.memberservice.models.Member;
import com.healthfirst.memberservice.exceptions.MemberNotFoundException;
import com.healthfirst.memberservice.repositories.MemberRepo;
import com.healthfirst.memberservice.services.MemberService;
import com.healthfirst.memberservice.enums.Gender;
import com.healthfirst.memberservice.enums.Interest;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberService.class)
public class MemberServiceTest {

    @Autowired
    MemberService service;

    @MockBean
    MemberRepo repo;

    @Test
    public void getAllMembers_ReturnsAllMembers() {
        // given
        Member bob = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123", Interest.ATHLETICS);
        List<Member> expected = Arrays.asList(bob);
        when(repo.findAll()).thenReturn(Arrays.asList(bob));

        // when
        List<Member> actual = service.getAllMembers();

        // then
        verify(repo, times(1)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getMemberById_ReturnsMemberWhenExists() {
        // given
        Member bob = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123", Interest.ATHLETICS);
        when(repo.findAll()).thenReturn(List.of(bob));

        // when
        Member actual = service.getMemberById(1L);

        // then
        Member expected = bob;
        verify(repo, times(1)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getMemberById_ThrowsExceptionWhenMemberDoesNotExist() {
        // given
        Long idThatDoesNotExist = 1L;
        when(repo.findById(idThatDoesNotExist)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> {
           service.getMemberById(idThatDoesNotExist);
            // then
        }).isInstanceOf(MemberNotFoundException.class)
                .hasMessage("Member not found with id " + idThatDoesNotExist);
    }

    @Test
    public void addMember_AddsWhenMemberValid() {
        // given
        Member bob = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123", Interest.ATHLETICS);

        // when
        service.addMember(bob);

        // then
        verify(repo, times(1)).save(bob);
    }

    @Test
    public void deleteMember_DeletesMemberWhenExists() {
        // given
        Long id = 1L;

        // when
        service.deleteMember(id);

        // then
        verify(repo, times(1)).deleteById(id);
    }

    @Test
    public void updateMember_UpdatesWhenMemberValid() {
        // given
        Member bob = new Member(1L, "bob", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123", Interest.ATHLETICS);
        when(repo.findAll()).thenReturn(List.of(bob));

        // when
        service.updateMember(1L, bob);

        // then
        verify(repo, times(1)).findAll();
    }
}