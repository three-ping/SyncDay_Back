package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.vo.AddCardboardVO;
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
        AddCardboardVO addCardboardVO = new AddCardboardVO();
        addCardboardVO.setTitle(title);
        addCardboardVO.setWorkspaceId(workspaceId);

        // when
        CardboardDTO newCardboard = appCardboardService.addCardboard(addCardboardVO);
        assertNotNull(newCardboard);

        assertEquals(title, newCardboard.getTitle());
        assertEquals(workspaceId, newCardboard.getWorkspaceId());
        log.info("newCardboard: {}", newCardboard);
    }
}