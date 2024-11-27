package com.threeping.syncday.cardbookmark.query.service;

import com.threeping.syncday.cardbookmark.query.aggregate.dto.CardBookmarkDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CardBookmarkServiceTests {

    @Autowired
    private CardBookmarkService cardBookmarkService;

    @DisplayName("유저ID로 카드 북마크 조회")
    @Test
    void testGetCardBookmarkByUserID(){

        // given
        Long userId =1L;

        // when
        List<CardBookmarkDTO> cardBookmarkDTOList = cardBookmarkService.getCardBookmarksByUserId(userId);

        // then
        assertNotNull(cardBookmarkDTOList);

        cardBookmarkDTOList.forEach(cardBookmarkDTO -> {
            log.info("cardBookmarkDTO: {}", cardBookmarkDTO);
            });
    }
}