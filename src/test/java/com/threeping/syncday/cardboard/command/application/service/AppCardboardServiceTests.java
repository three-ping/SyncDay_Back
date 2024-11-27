package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.vo.AppCardboardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class AppCardboardServiceTests {

    @Autowired
    private AppCardboardService appCardboardService;

    @DisplayName("카드보드 생성 테스트")
    @Test
    void testCreateCardboard(){

        // given
        String title = "카드보드 생성 테스트";
        Long workspaceId = 1L;
        AppCardboardVO appCardboardVO = new AppCardboardVO();
        appCardboardVO.setTitle(title);
        appCardboardVO.setWorkspaceId(workspaceId);

        // when
        CardboardDTO newCardboard = appCardboardService.addCardboard(appCardboardVO);
        assertNotNull(newCardboard);

        assertEquals(title, newCardboard.getTitle());
        assertEquals(workspaceId, newCardboard.getWorkspaceId());
        log.info("newCardboard: {}", newCardboard);
    }


    @DisplayName("카드보드 수정 테스트")
    @Test
    void testModifyCardboard(){

        // given
        Long cardBoardId = 1L;
        String modifyCardboardTitle = "카드보드 수정 테스트";
        Byte progressStatus = 1;
        AppCardboardVO appCardboardVO = new AppCardboardVO();
        appCardboardVO.setCardboardId(cardBoardId);
        appCardboardVO.setProgressStatus(progressStatus);
        appCardboardVO.setTitle(modifyCardboardTitle);
        // when
        CardboardDTO modifiedCardboardDTO = appCardboardService.modifyCardboard(appCardboardVO);

        assertNotNull(modifiedCardboardDTO);

        log.info("modifiedCardboardDTO: {}", modifiedCardboardDTO);
    }
}