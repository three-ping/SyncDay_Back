package com.threeping.syncday.cardboard.query.service;

import com.threeping.syncday.cardboard.query.aggregate.CardBoardDTO;
import com.threeping.syncday.workspace.query.aggregate.WorkspaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CardBoardServiceTests {

    @Autowired
    CardBoardService cardBoardService;


    @DisplayName("카드보드 전체 조회")
    @Test
    void testGetAllCardBoards() {
        // given

        // when
        List<CardBoardDTO> cardBoards = cardBoardService.getAllCardBoards();

        // then
        assertNotNull(cardBoards);
        cardBoards.forEach(cardBoardDTO -> {
            log.info("cardBoardDTO: {}", cardBoardDTO);
        });



    }
    @DisplayName("워크스페이스 ID로 카드보드 조회")
    @Test
    void testGetCardBoardsByWorkspaceId(){

        // given
        Long workspaceId = 1L;

        // when
        List<CardBoardDTO> cardBoards = cardBoardService.getCardBoardsByWorkspaceId(workspaceId);

        // then
        assertNotNull(cardBoards);

        cardBoards.forEach(cardBoardDTO -> {
            log.info("cardBoardDTO: {}", cardBoardDTO);
        });

    }
}