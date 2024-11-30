package com.threeping.syncday.cardbookmark.command.application.service;
import com.threeping.syncday.cardbookmark.command.aggregate.dto.CardBookmarkDTO;
import com.threeping.syncday.cardbookmark.command.aggregate.entity.CardBookmark;
import com.threeping.syncday.cardbookmark.command.domain.repository.CardBookmarkRepository;
import com.threeping.syncday.common.exception.CommonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppCardBookmarkServiceTests {

    @Mock
    private CardBookmarkRepository cardBookmarkRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AppCardBookmarkServiceImpl appCardBookmarkService;

    private Long userId;
    private Long cardId;
    private CardBookmark cardBookmark;
    private CardBookmarkDTO cardBookmarkDTO;

    @BeforeEach
    void setUp() {
        userId = 1L;
        cardId = 1L;

        cardBookmark = new CardBookmark();
        cardBookmark.setUserId(userId);
        cardBookmark.setCardId(cardId);

        cardBookmarkDTO = new CardBookmarkDTO();
        // DTO 필드 설정
    }

    @Nested
    @DisplayName("북마크 추가 테스트")
    class AddBookmarkTest {

        @Test
        @DisplayName("북마크 추가 성공")
        void addBookmark_Success() {
            // given
            when(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId))
                    .thenReturn(false);
            when(cardBookmarkRepository.save(any(CardBookmark.class)))
                    .thenReturn(cardBookmark);
            when(modelMapper.map(cardBookmark, CardBookmarkDTO.class))
                    .thenReturn(cardBookmarkDTO);

            // when
            CardBookmarkDTO result = appCardBookmarkService.addBookmark(userId, cardId);

            // then
            assertNotNull(result);
            verify(cardBookmarkRepository).existsByUserIdAndCardId(userId, cardId);
            verify(cardBookmarkRepository).save(any(CardBookmark.class));
            verify(modelMapper).map(cardBookmark, CardBookmarkDTO.class);
        }

        @Test
        @DisplayName("이미 존재하는 북마크 추가 시도")
        void addBookmark_AlreadyExists() {
            // given
            when(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId))
                    .thenReturn(true);

            // when & then
            assertThrows(CommonException.class,
                    () -> appCardBookmarkService.addBookmark(userId, cardId));
            verify(cardBookmarkRepository).existsByUserIdAndCardId(userId, cardId);
            verify(cardBookmarkRepository, never()).save(any(CardBookmark.class));
        }
    }

    @Nested
    @DisplayName("북마크 제거 테스트")
    class RemoveBookmarkTest {

        @Test
        @DisplayName("북마크 제거 성공")
        void removeBookmark_Success() {
            // given
            when(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId))
                    .thenReturn(false);
            when(cardBookmarkRepository.deleteByUserIdAndCardId(userId, cardId))
                    .thenReturn(true);

            // when
            Boolean result = appCardBookmarkService.removeBookmark(userId, cardId);

            // then
            assertTrue(result);
            verify(cardBookmarkRepository).existsByUserIdAndCardId(userId, cardId);
            verify(cardBookmarkRepository).deleteByUserIdAndCardId(userId, cardId);
        }

        @Test
        @DisplayName("존재하지 않는 북마크 제거 시도")
        void removeBookmark_NotExists() {
            // given
            when(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId))
                    .thenReturn(true);

            // when & then
            assertThrows(CommonException.class,
                    () -> appCardBookmarkService.removeBookmark(userId, cardId));
            verify(cardBookmarkRepository).existsByUserIdAndCardId(userId, cardId);
            verify(cardBookmarkRepository, never()).deleteByUserIdAndCardId(userId, cardId);
        }

        @Test
        @DisplayName("북마크 제거 중 예외 발생")
        void removeBookmark_ThrowsException() {
            // given
            when(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId))
                    .thenReturn(false);
            when(cardBookmarkRepository.deleteByUserIdAndCardId(userId, cardId))
                    .thenThrow(new RuntimeException());

            // when & then
            assertThrows(CommonException.class,
                    () -> appCardBookmarkService.removeBookmark(userId, cardId));
            verify(cardBookmarkRepository).existsByUserIdAndCardId(userId, cardId);
            verify(cardBookmarkRepository).deleteByUserIdAndCardId(userId, cardId);
        }
    }
}