package com.healthfirst.memberservice.unit;

import com.healthfirst.memberservice.configuration.Configuration;
import com.healthfirst.memberservice.controllers.MemberController;
import com.healthfirst.memberservice.enums.Gender;
import com.healthfirst.memberservice.enums.Interest;
import com.healthfirst.memberservice.exceptions.MemberNotFoundException;
import com.healthfirst.memberservice.models.Member;
import com.healthfirst.memberservice.services.MemberService;

import com.healthfirst.memberservice.utils.JsonUtil;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Configuration configuration;

    @MockBean
    private MemberService service;

    private final Member BOB = new Member(
        1L,
        "bob",
        "marley",
        21,
        Gender.MALE,
        "bob@gmail.com",
        "pass1234",
        List.of(Interest.ATHLETICS));

    @Test
    public void getAllMembers_HappyPath() throws Exception {
        when(service.getAllMembers()).thenReturn(Arrays.asList(BOB));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("bob")))
                .andExpect(jsonPath("$[0].lastName", is("marley")))
                .andExpect(jsonPath("$[0].age", is(21)))
                .andExpect(jsonPath("$[0].gender", is(Gender.MALE.toString())))
                .andExpect(jsonPath("$[0].email", is("bob@gmail.com")))
                .andExpect(jsonPath("$[0].password", is("pass1234")))
                .andExpect(jsonPath("$[0].interests", is(List.of(Interest.ATHLETICS.toString()))));

        verify(service, times(1)).getAllMembers();
    }

    @Test
    public void getMemberById_HappyPath() throws Exception {
        when(service.getMemberById(1L)).thenReturn(BOB);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/members/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("bob")))
                .andExpect(jsonPath("$.lastName", is("marley")))
                .andExpect(jsonPath("$.age", is(21)))
                .andExpect(jsonPath("$.gender", is(Gender.MALE.toString())))
                .andExpect(jsonPath("$.email", is("bob@gmail.com")))
                .andExpect(jsonPath("$.password", is("pass1234")))
                .andExpect(jsonPath("$.interests", is(List.of(Interest.ATHLETICS.toString()))));

        verify(service, times(1)).getMemberById(1L);
    }


    @Test
    public void getMemberById_MemberDoesNotExist() throws Exception {
        when(service.getMemberById(1L)).thenThrow (new MemberNotFoundException("member id not found"));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/members/1")
                        .contentType(MediaType.APPLICATION_JSON))
                         .andDo(print())
                         .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MemberNotFoundException))
                .andExpect(result -> assertEquals("member id not found", result.getResolvedException().getMessage()));
        verify(service, times(1)).getMemberById(1L);

    }
    @Test
    public void addMember_HappyPath() throws Exception {
        when(service.addMember(BOB)).thenReturn(BOB);
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(BOB)));
        verify(service, times(1)).addMember(BOB);
    }

    @Test
    public void deleteMember_HappyPath() throws Exception {
        mockMvc.perform(delete("/members/1"));
        verify(service, times(1)).deleteMember(1L);
    }

    @Test
    public void updateMember_HappyPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/members/1")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(BOB)));
        verify(service, times(1)).updateMember(1L, BOB);
    }

    @Test
    public void updateMember_MemberDoesNotExist() throws Exception {
        when(service.updateMember(1L, BOB)).thenThrow(new MemberNotFoundException("member not found"));
        mockMvc.perform(MockMvcRequestBuilders.put("/members/1")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(BOB)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MemberNotFoundException))
                .andExpect(result -> assertEquals("member not found", result.getResolvedException().getMessage()));
        verify(service, times(1)).updateMember(1L, BOB);
    }
}