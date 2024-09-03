package com.example.swimServer.interfaces.api.record;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @Resource
    private MockMvc mvc;

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
                "2, 'backstroke', 100, 60.0",
                "3, 'freestyle_relay', 200, 120.0",})
    void CanPostRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/record")
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ", \"time_s\": " + time_s + "}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }

    @ParameterizedTest
    @Tag("medium")
    @CsvSource({"1, 'freestyle', 125, 25.0",
                "2, 'backstroke', 400, 60.0"})
    void CanFailInvalidRecord(String swimmerId, String swimStyle, String distance, String time_s) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/record")
                .contentType("application/json")
                .content("{\"swimmerId\": " + swimmerId + ", \"swimStyle\": \"" + swimStyle + "\", \"distance\": " + distance + ", \"time_s\": " + time_s + "}"))
                .andExpect(status().isBadRequest());
    }
}
