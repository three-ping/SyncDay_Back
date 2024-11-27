package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.CardboardDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CardboardServiceTests {

    @Autowired
    CardboardService cardBoardService;


    @DisplayName("카드보드 전체 조회")
    @Test
    void testGetAllCardBoards() {
        // given

        // when
        List<CardboardDTO> cardBoards = cardBoardService.getAllCardBoards();

        // then
        assertNotNull(cardBoards);
        cardBoards.forEach(cardboardDTO -> {
            log.info("cardBoardDTO: {}", cardboardDTO);
        });



    }
    @DisplayName("워크스페이스 ID로 카드보드 조회")
    @Test
    void testGetCardBoardsByWorkspaceId(){

        // given
        Long workspaceId = 1L;

        // when
        List<CardboardDTO> cardBoards = cardBoardService.getCardBoardsByWorkspaceId(workspaceId);

        // then
        assertNotNull(cardBoards);

        cardBoards.forEach(cardboardDTO -> {
            log.info("cardBoardDTO: {}", cardboardDTO);
        });

    }
}