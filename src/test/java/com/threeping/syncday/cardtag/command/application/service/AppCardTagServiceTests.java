package com.threeping.syncday.cardtag.command.application.service;

import com.threeping.syncday.cardtag.command.aggregate.dto.CardTagDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@Slf4j
@SpringBootTest
@Transactional
class AppCardTagServiceTests {

    @Autowired
    private AppCardTagService appCardTagService;

    @DisplayName("카드 태그 생성 테스트")
    @Test
    void testaddCardTag(){

        // given
        CardTagDTO cardTagDTO = new CardTagDTO();
        cardTagDTO.setTagName("카드 태그 생성ㅅ 테스트");
        cardTagDTO.setWorkspaceId(1L);
        cardTagDTO.setColor("#FFFFFF");

        // when
        CardTagDTO addedCard = appCardTagService.addCardTag(cardTagDTO);


        // then
        assertNotNull(addedCard);
        log.info("addedCard: {}", addedCard);
    }


    @DisplayName("카드 태그 수정 테스트")
    @Test
    void testModifyCardTag(){
        // given
        CardTagDTO cardTagDTO = new CardTagDTO();
        cardTagDTO.setTagId(1L);
        cardTagDTO.setColor("#000000");

        // when
        CardTagDTO updatedCard = appCardTagService.modifyCardTag(cardTagDTO);

        // then
        assertNotNull(updatedCard);
        log.info("updatedCard: {}", updatedCard);
    }

    @DisplayName("카드 태그 삭제 테스트")
    @Test
    void testDeleteCardTag(){

        // given
        Long cardTagId = 1L;

        // when
        Boolean result = appCardTagService.removeCardTag(cardTagId);

        // then
        assertEquals(Boolean.TRUE, result);
        log.info("result: {}", result);
    }
}