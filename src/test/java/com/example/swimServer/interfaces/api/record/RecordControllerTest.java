package com.example.swimServer.interfaces.api.record;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithMockAuthentication;
import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcResourceServerSecurity;
import com.example.swimServer.config.SecurityConfig;
import com.example.swimServer.domain.service.RaceRecordService;
import com.example.swimServer.interfaces.dto.SwimmerDto;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class RecordControllerTest {

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

    @Resource
    private MockMvc mvc;

    @BeforeEach
    void setup() {

    }

    @Test
    @Tag("large")
    void CanGetRecordAsJson() throws Exception {
         mvc.perform(MockMvcRequestBuilders.get("/record").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @ParameterizedTest
    @Tag("large")
    @CsvSource({"1, 'freestyle', 50, 25.0",
                "1, 'backstroke', 100, 60.0",
                "1, 'freestyle_relay', 200, 120.0",})
    void CanPostRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        SwimmerDto dummySwimmer = new SwimmerDto();
        dummySwimmer.familyName = "Doe";
        dummySwimmer.givenName = "John";
        dummySwimmer.age = 25;

        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer").with(jwt())
                .contentType("application/json")
                .content("{\"familyName\": \"" + dummySwimmer.familyName + "\", \"givenName\": \"" + dummySwimmer.givenName + "\", \"age\": " + dummySwimmer.age + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));

        mvc.perform(MockMvcRequestBuilders.post("/record").with(jwt())
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ",\"time_s\": " + time_s + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @ParameterizedTest
    @Tag("large")
    @CsvSource({"1, 'freestyle', 125, 25.0",
                "2, 'backstroke', 400, 60.0"})
    void CanFailInvalidRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        SwimmerDto dummySwimmer = new SwimmerDto();
        dummySwimmer.familyName = "Doe";
        dummySwimmer.givenName = "John";
        dummySwimmer.age = 25;
        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer").with(jwt())
                .contentType("application/json")
                .content("{\"familyName\": \"" + dummySwimmer.familyName + "\", \"givenName\": \"" + dummySwimmer.givenName + "\", \"age\": " + dummySwimmer.age + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));

        mvc.perform(MockMvcRequestBuilders.post("/record").with(jwt())
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ",\"time_s\": " + time_s + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("large")
    void CanGetAllRecords() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/record").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }
}
