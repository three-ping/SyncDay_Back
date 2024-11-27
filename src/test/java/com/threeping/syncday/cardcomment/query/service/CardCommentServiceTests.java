package com.threeping.syncday.cardcomment.query.service;

import com.threeping.syncday.cardcomment.query.aggregate.dto.CardCommentDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Slf4j
class CardCommentServiceTests {

    @Autowired
    private CardCommentService cardCommentService;

    @DisplayName("카드 ID로 댓글 조회")
    @Test
    void testFindCommentsByCardId(){

        // given
        Long cardId = 11L;

        // when
        List<CardCommentDTO> cardCommentDTOS = cardCommentService.getCommentsByCardId(cardId);

        // then
        assertNotNull(cardCommentDTOS);
        cardCommentDTOS.forEach(x-> log.info("x: {}", x));
    }


}