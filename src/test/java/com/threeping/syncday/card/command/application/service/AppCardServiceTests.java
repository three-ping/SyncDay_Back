package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.dto.CardDTO;
import com.threeping.syncday.card.command.aggregate.vo.AppCardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class AppCardServiceTests {

    @Autowired
    private AppCardService appCardService;

    @DisplayName("카드 생성 테스트")
    @Test
    void testAddCard(){

        // given
        AppCardVO appCardVO = new AppCardVO();
        appCardVO.setTitle("카드생성테스트");
        appCardVO.setCreatedBy(1L);
        appCardVO.setCardboardId(1L);
        // when
        CardDTO cardDTO = appCardService.addCard(appCardVO);

        // then
        assertNotNull(cardDTO);
        log.info("cardDTO: {}", cardDTO);
    }
}