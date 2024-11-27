package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestAddCardCommentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Slf4j
class AppCardCommentServiceTests {

    @Autowired
    private AppCardCommentService appCardCommentService;

    @DisplayName("카드 댓글 생성 테스트")
    @Test
    void testAddCardComment(){

        // given
        RequestAddCardCommentVO cardCommentVO = new RequestAddCardCommentVO("카드댓글생성테스트",1L, 1L);

        // when
        CardCommentDTO cardCommentDTO = appCardCommentService.addCardComment(cardCommentVO);

        // then
        assertNotNull(cardCommentDTO);
        log.info("cardCommentDTO: {}", cardCommentDTO);
    }
}