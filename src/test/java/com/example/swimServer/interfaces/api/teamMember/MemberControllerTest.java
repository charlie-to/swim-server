package com.example.swimServer.interfaces.api.teamMember;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Test
    void CanAddTeamMember(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer/add")
            .contentType("application/json")
            .content("{\"familyName\":\"Doe\",\"givenName\":\"John\",\"age\":25}"))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"message\":\"Swimmer added\"}"));
    }

    @Test
    void CanGetAllTeamMembers(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/member/swimmer/all"))
            .andExpect(status().isOk());
    }
}
