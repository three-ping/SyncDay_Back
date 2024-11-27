package com.threeping.syncday.card.command.application.service;

import com.threeping.syncday.card.command.aggregate.dto.CardDTO;
import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.card.command.aggregate.vo.RequestUpdateCardVO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
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
        RequestUpdateCardVO requestUpdateCardVO = new RequestUpdateCardVO();
        requestUpdateCardVO.setTitle("카드생성테스트");
        requestUpdateCardVO.setUserId(1L);
        requestUpdateCardVO.setCardboardId(1L);
        // when
        CardDTO cardDTO = appCardService.addCard(requestUpdateCardVO);

        // then
        assertNotNull(cardDTO);
        log.info("cardDTO: {}", cardDTO);
    }

    @DisplayName("카드 수정 성공 테스트")
    @Test
    void testModifyCardSuccess() {
        // given
        RequestUpdateCardVO requestUpdateCardVO = new RequestUpdateCardVO();
        requestUpdateCardVO.setCardId(1L);
        requestUpdateCardVO.setUserId(1L);  // 권한 있는 사용자
        requestUpdateCardVO.setTitle("카드 수정 테스트");

        // when
        CardDTO cardDTO = appCardService.modifyCard(requestUpdateCardVO);

        // then
        assertNotNull(cardDTO);
        log.info("수정된 cardDTO: {}", cardDTO);
    }

    @DisplayName("카드 수정 실패 테스트 - 권한 없는 사용자")
    @Test
    void testModifyCardUnauthorized() {
        // given
        RequestUpdateCardVO requestUpdateCardVO = new RequestUpdateCardVO();
        requestUpdateCardVO.setCardId(1L);
        requestUpdateCardVO.setUserId(2L);  // 권한 없는 사용자
        requestUpdateCardVO.setTitle("카드 수정 테스트");

        // when & then
        assertThrows(CommonException.class, () ->
                        appCardService.modifyCard(requestUpdateCardVO),
                "권한 없는 사용자의 카드 수정 요청은 CommonException이 발생해야 합니다"
        );
    }

    @DisplayName("카드 수정 실패 테스트 - 존재하지 않는 카드")
    @Test
    void testModifyNonExistentCard() {
        // given
        RequestUpdateCardVO requestUpdateCardVO = new RequestUpdateCardVO();
        requestUpdateCardVO.setCardId(999L);  // 존재하지 않는 카드
        requestUpdateCardVO.setUserId(1L);
        requestUpdateCardVO.setTitle("카드 수정 테스트");

        // when & then
        assertThrows(CommonException.class, () ->
                        appCardService.modifyCard(requestUpdateCardVO),
                "존재하지 않는 카드 수정 요청은 CommonException이 발생해야 합니다"
        );
    }

    @DisplayName("카드 삭제 테스트")
    @Test
    void testDeleteCard(){

        // given
        Long userId = 1L;
        Long cardId = 1L;
        RequestDeleteCardVO requestDeleteCardVO = new RequestDeleteCardVO(cardId,userId);

        // when
        Boolean deleteCardResult = appCardService.removeCard(requestDeleteCardVO);

        // then
        assertTrue(deleteCardResult);
    }

    @DisplayName("카드 삭제 실패 테스트 - 권한 없는 사용자")
    @Test
    void testDeleteFailCard() {
        // given
        Long userId = 2L;
        Long cardId = 1L;
        RequestDeleteCardVO requestDeleteCardVO = new RequestDeleteCardVO(cardId, userId);

        // when & then
        assertThrows(CommonException.class, () ->
                        appCardService.removeCard(requestDeleteCardVO),
                "권한 없는 사용자의 카드 삭제 요청은 CommonException이 발생해야 합니다"
        );
    }

    @Test
    @DisplayName("카드 삭제 실패 테스트 - 존재하지 않는 카드")
    void testDeleteNonExistentCard() {
        // given
        Long nonExistentCardId = 999L;
        RequestDeleteCardVO requestDeleteCardVO = new RequestDeleteCardVO(nonExistentCardId, 1L);

        // when & then
        CommonException exception = assertThrows(CommonException.class, () ->
                appCardService.removeCard(requestDeleteCardVO)
        );
        assertEquals(ErrorCode.CARD_NOT_FOUND, exception.getErrorCode());
    }
}