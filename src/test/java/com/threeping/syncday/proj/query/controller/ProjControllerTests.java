package com.threeping.syncday.proj.query.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProjControllerTests {

    @Autowired
    private ProjController projController;

    @Autowired
    private MockMvc mockMvc;



    @DisplayName("프로젝트 ID를 통한 조회 테스트")
    @Test
    void testGetProjById() throws Exception{

        // given
        Long projId = 1L;

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/projs/{projId}",projId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists())  // 객체가 존재하는지만 확인
                .andExpect(jsonPath("$.data.proj_id").exists())  // projId가 있는지 확인
                .andDo(print());

        String responseContent = resultActions.andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8);
        log.info("Test Result: {}", responseContent);
    }
}