package com.threeping.syncday.card.query.service;

import com.threeping.syncday.card.query.aggregate.CardDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CardServiceTests {


    @Autowired
    CardService cardService;


    @DisplayName("카드 전체 조회 테스트")
    @Test
    void testGetAllCards(){

        // given

        // when
        List<CardDTO> cards = cardService.getAllCards();

        // then
        assertNotNull(cards);

        cards.forEach(cardDTO -> log.info("cardDTO: {}", cardDTO));

    }
}