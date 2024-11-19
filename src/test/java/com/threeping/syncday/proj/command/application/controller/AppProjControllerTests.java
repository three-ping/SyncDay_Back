package com.threeping.syncday.proj.command.application.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class AppProjControllerTests {

    @Autowired
    private AppProjController appProjController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            java.nio.charset.StandardCharsets.UTF_8
    );

    @DisplayName("프로젝트 생성 테스트")
    @Test
    void testAddProject() throws Exception {
        // given
        Long userId = 1L;
        String projName = "프로젝트 생성 테스트";

        ProjVO projVO = new ProjVO();
        projVO.setUserId(userId);
        projVO.setProjName(projName);

        String content = objectMapper.writeValueAsString(projVO);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/api/proj/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content(content)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists())
                .andDo(print()); // 테스트 결과를 보기 쉽게 출력

        log.info("Test Result: {}", resultActions.andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8));
    }
}