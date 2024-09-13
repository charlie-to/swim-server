package com.example.swimServer.interfaces.api.teamMember;

import com.c4_soft.springaddons.security.oauth2.test.webflux.AutoConfigureAddonsWebfluxResourceServerSecurity;
import com.example.swimServer.infrastructure.persistance.maria.swimmer.SwimmerRepository;
import com.github.dockerjava.api.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
@AutoConfigureAddonsWebfluxResourceServerSecurity
@Testcontainers
class MemberControllerTest {

    @MockBean
    private AuthenticationManagerResolver<?> authenticationManagerResolver;

    @Container
    private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:latest");

    @DynamicPropertySource
    static void setup(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);
    }

    private MockMvc mockMvc;

    @MockBean
    private SwimmerRepository swimmerRepository;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @Tag("large")
    void CanAddTeamMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/member/swimmer")
            .contentType("application/json")
            .content("{\"familyName\":\"Doe\",\"givenName\":\"John\",\"age\":25}"))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"message\":\"Swimmer added\"}"));
    }

    @Test
    @Tag("large")
    void CanGetAllTeamMembers(@Autowired MockMvc mvc) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/member/swimmer/all"))
            .andExpect(status().isOk());
    }
}
