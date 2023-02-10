package com.healthfirst.memberservice.unit;

import com.healthfirst.memberservice.exceptions.MemberWithEmailAlreadyExists;
import com.healthfirst.memberservice.feign.WelcomeServiceCalls;
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
    WelcomeServiceCalls welcomeServiceCalls;

    @MockBean
    MemberRepo repo;

    private final Member BOB = new Member(
        1L,
        "bob",
        "marley",
        21,
        Gender.MALE,
        "bob@gmail.com",
        "pass123",
        List.of(Interest.ATHLETICS));

    @Test
    public void getAllMembers_ReturnsAllMembers() {
        // given
        List<Member> expected = Arrays.asList(BOB);
        when(repo.findAll()).thenReturn(Arrays.asList(BOB));

        // when
        List<Member> actual = service.getAllMembers();

        // then
        verify(repo, times(1)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getMemberById_ReturnsMemberWhenExists() {
        // given
        when(repo.findAll()).thenReturn(List.of(BOB));

        // when
        Member actual = service.getMemberById(1L);

        // then
        Member expected = BOB;
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
        // when
        service.addMember(BOB);

        // then
        verify(repo, times(1)).save(BOB);
        verify(welcomeServiceCalls, times(1)).sendNewMemberToWelcomeService(BOB);
    }

    @Test
    public void addMember_ThrowsExceptionWhenMemberEmailAlreadyExists() {
        // given
        Long idThatDoesNotExist = 1L;
        when(repo.findAll()).thenReturn(List.of(BOB));

        // when
        assertThatThrownBy(() -> {
            service.addMember(BOB);
            // then
        }).isInstanceOf(MemberWithEmailAlreadyExists.class)
            .hasMessage("member with the email " + BOB.getEmail() + " already exists");
        verify(repo, times(0)).save(BOB);
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
        Member bobUpdated = new Member(1L, "bobby", "marley", 21, Gender.MALE, "bob@gmail.com", "pass123",
            List.of(Interest.ATHLETICS));

        when(repo.findAll()).thenReturn(List.of(BOB));

        // when
        service.updateMember(1L, bobUpdated);

        // then
        verify(repo, times(1)).findAll();
        verify(repo, times(1)).save(bobUpdated);
    }

    @Test
    public void updateMember_ThrowsExceptionWhenMemberDoesNotExist() {
        // given
        Long idThatDoesNotExist = 1L;
        when(repo.findAll()).thenReturn(List.of());

        // when
        assertThatThrownBy(() -> {
            service.updateMember(idThatDoesNotExist, BOB);
            // then
        }).isInstanceOf(MemberNotFoundException.class)
                .hasMessage("Member not found with id " + idThatDoesNotExist);
        verify(repo, times(0)).save(BOB);
    }
}