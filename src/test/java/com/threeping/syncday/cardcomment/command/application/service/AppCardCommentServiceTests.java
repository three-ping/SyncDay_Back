package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestCreateCardCommentVO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestUpdateCardCommentVO;
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
        RequestCreateCardCommentVO cardCommentVO = new RequestCreateCardCommentVO("카드댓글생성테스트",1L, 1L);

        // when
        CardCommentDTO cardCommentDTO = appCardCommentService.addCardComment(cardCommentVO);

        // then
        assertNotNull(cardCommentDTO);
        log.info("cardCommentDTO: {}", cardCommentDTO);
    }

    @DisplayName("카드댓글 수정 테스트")
    @Test
    void testModifyCardComment(){

        // given
        Long userId = 2L;
        String content = "카드댓글내용수정";
        Long cardCommentId = 1L;

        RequestUpdateCardCommentVO cardCommentVO = new RequestUpdateCardCommentVO(content, userId, cardCommentId);

        // when
        CardCommentDTO cardCommentDTO = appCardCommentService.modifyCardComment(cardCommentVO);

        // then
        assertNotNull(cardCommentDTO);
        log.info("cardCommentDTO1: {}", cardCommentDTO);
    }

    @DisplayName("카드 댓글 삭제 테스트")
    @Test
    void testDeleteCardComment(){

        // given
        Long userId = 2L;
        Long commentId = 1L;

        // when
        RequestDeleteCardVO req = new RequestDeleteCardVO(commentId,userId);
        Boolean res = appCardCommentService.removeCardComment(req);

        // then
        assertNotNull(res);

        log.info("res: {}", res);
    }
}