package com.example.swimServer.interfaces.api.record;

import com.example.swimServer.interfaces.dto.SwimmerDto;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class RecordControllerTest {

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
    @Tag("medium")
    void CanGetRecordAsJson() throws Exception {
         mvc.perform(MockMvcRequestBuilders.get("/record"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @ParameterizedTest
    @Tag("medium")
    @CsvSource({"1, 'freestyle', 50, 25.0",
                "1, 'backstroke', 100, 60.0",
                "1, 'freestyle_relay', 200, 120.0",})
    void CanPostRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        SwimmerDto dummySwimmer = new SwimmerDto();
        dummySwimmer.familyName = "Doe";
        dummySwimmer.givenName = "John";
        dummySwimmer.age = 25;

        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer")
                .contentType("application/json")
                .content("{\"familyName\": \"" + dummySwimmer.familyName + "\", \"givenName\": \"" + dummySwimmer.givenName + "\", \"age\": " + dummySwimmer.age + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));

        mvc.perform(MockMvcRequestBuilders.post("/record")
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ",\"time_s\": " + time_s + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @ParameterizedTest
    @Tag("medium")
    @CsvSource({"1, 'freestyle', 125, 25.0",
                "2, 'backstroke', 400, 60.0"})
    void CanFailInvalidRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        SwimmerDto dummySwimmer = new SwimmerDto();
        dummySwimmer.familyName = "Doe";
        dummySwimmer.givenName = "John";
        dummySwimmer.age = 25;
        mvc.perform(MockMvcRequestBuilders.post("/member/swimmer")
                .contentType("application/json")
                .content("{\"familyName\": \"" + dummySwimmer.familyName + "\", \"givenName\": \"" + dummySwimmer.givenName + "\", \"age\": " + dummySwimmer.age + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));

        mvc.perform(MockMvcRequestBuilders.post("/record")
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ",\"time_s\": " + time_s + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("medium")
    void CanGetAllRecords() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/record"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }
}
