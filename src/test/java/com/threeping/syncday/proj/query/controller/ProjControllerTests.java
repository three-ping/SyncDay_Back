package com.threeping.syncday.proj.query.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProjControllerTests {

    @Autowired
    private ProjController projController;

    @Autowired
    private MockMvc mockMvc;


    @DisplayName("프로젝트 전체 조회 테스트")
    @Test
    void testGetAllProblems() throws Exception{
        mockMvc.perform(get("/api/proj/"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}