package com.threeping.syncday.cardtag.query.service;

import com.threeping.syncday.cardtag.query.aggregate.dto.CardTagDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CardTagServiceTests {

    @Autowired
    private CardTagService cardTagService;

    @DisplayName("카드 태그 ID로 조회")
    @Test
    void testGetCardTagById(){

        // given
        Long cardTagId = 1L;

        // when
        CardTagDTO cardTagDTO = cardTagService.getCardTagById(cardTagId);

        // then
        assertNotNull(cardTagDTO);

        log.info("cardTagDTO: {}", cardTagDTO);
    }
}