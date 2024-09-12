package com.example.swimServer.interfaces.api.teamMember;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class MemberControllerTest {

    @Container
    private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:latest");

    @DynamicPropertySource
    static void setup(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
    }


    @Test
    @Tag("medium")
    void CanAddTeamMember(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer")
            .contentType("application/json")
            .content("{\"familyName\":\"Doe\",\"givenName\":\"John\",\"age\":25}"))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"message\":\"Swimmer added\"}"));
    }

    @Test
    @Tag("medium")
    void CanGetAllTeamMembers(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/member/swimmer/all"))
            .andExpect(status().isOk());
    }
}
