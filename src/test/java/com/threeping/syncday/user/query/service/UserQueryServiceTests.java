package com.threeping.syncday.user.query.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.application.dto.UserDTO;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.query.repository.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTests {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    @DisplayName("이메일로 사용자 조회 성공 테스트")
    void findByEmail_Success() {
        // given
        String email = "test@test.com";
        UserEntity mockEntity = new UserEntity();
        mockEntity.setUserId(1L);
        mockEntity.setEmail(email);
        mockEntity.setJoinYear(Timestamp.valueOf(LocalDateTime.now()));
        mockEntity.setPassword("password");
        mockEntity.setUserName("testUser");
        mockEntity.setTeamId(1L);
        mockEntity.setLastAccessTime(Timestamp.valueOf(LocalDateTime.now()));

        when(userMapper.findByEmail(email)).thenReturn(mockEntity);

        // when
        UserDTO result = userQueryService.findByEmail(email);

        // then
        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getUserName(), "testUser");
        verify(userMapper, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("존재하지 않은 이메일로 사용자 조회 실패 테스트")
    void findByEmail_UserNotFound() {
        // given
        String email = "nonexistent@test.com";
        when(userMapper.findByEmail(email)).thenReturn(null);

        // when & then
        CommonException e = assertThrows(CommonException.class,
                () -> userQueryService.findByEmail(email));
        assertEquals(e.getErrorCode(),(ErrorCode.NOT_FOUND_USER));
        verify(userMapper, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("loadUserByUserName 성공 테스트")
    void loadUserByUserName_Success() {
        // given
        String email = "test@test.com";
        String password = "encodedPassword";
        UserEntity mockEntity = new UserEntity();
        mockEntity.setUserId(1L);
        mockEntity.setEmail(email);
        mockEntity.setJoinYear(Timestamp.valueOf(LocalDateTime.now()));
        mockEntity.setPassword(password);
        mockEntity.setUserName("testUser");
        mockEntity.setTeamId(1L);
        mockEntity.setLastAccessTime(Timestamp.valueOf(LocalDateTime.now()));

        when(userMapper.findByEmail(email)).thenReturn(mockEntity);

        // when
        UserDetails result = userQueryService.loadUserByUsername(email);

        // then
        assertNotNull(result);
        assertEquals(result.getUsername(), email);
        assertEquals(result.getPassword(), password);
        assertTrue(result.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
        verify(userMapper, times(1)).findByEmail(email);
    }
}