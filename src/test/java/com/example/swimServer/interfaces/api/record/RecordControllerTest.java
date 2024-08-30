package com.example.swimServer.interfaces.api.record;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest {

    @Test
    void CanGetRecordAsJson(@Autowired MockMvc mvc) throws Exception {
         mvc.perform(MockMvcRequestBuilders.get("/record"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Type"))
                .andExpect(header().string("Content-Type", "application/json"));
    }
}
