package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserCommandServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @Test
    @DisplayName("비밀번호 변경 성공 테스트")
    void updatePwdSuccess() {
        // given
        Long userId = 1L;
        String userName = "testName";
        String currentPwd = "oldPassword";
        String newPwd = "newPassword";
        String email = "test@test.com";
        String encodedOldPwd = "encodedOldPassword";
        String encodedNewPwd = "encodedNewPassword";
        Long teamId = 1L;
        UserEntity newUser = new UserEntity();
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setPassword(encodedOldPwd);
        newUser.setEmail(email);
        newUser.setTeamId(teamId);

        when(bCryptPasswordEncoder.encode(newPwd)).thenReturn(encodedNewPwd);
        when(bCryptPasswordEncoder.matches(currentPwd, encodedOldPwd)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(newUser));

        // when
        userCommandService.updatePassword(userId, currentPwd, newPwd);

        // then
        verify(userRepository).save(any(UserEntity.class)); // repository 저장여부 검증
        assertTrue(newUser.getPassword().equals(encodedNewPwd)); // 바뀐 비밀번호 검증
    }

    @Test
    @DisplayName("비밀번호 변경 실패 - 현재 비밀번호 불일치")
    void updatePwdFailWithWrongPwd() {
        // given
        Long userId = 1L;
        String userName = "testName";
        String currentPwd = "wrongPwd";
        String newPwd = "newPwd";
        String email = "test@test.com";
        String encodedOldPwd = "encodedOldPassword";
        Long teamId = 1L;
        UserEntity newUser = new UserEntity();
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setPassword(encodedOldPwd);
        newUser.setEmail(email);
        newUser.setTeamId(teamId);

        // when
        when(userRepository.findById(userId)).thenReturn(Optional.of(newUser));
        when(bCryptPasswordEncoder.matches(currentPwd, encodedOldPwd)).thenReturn(false);

        // then
        CommonException e = assertThrows(CommonException.class,
                () -> userCommandService.updatePassword(userId, currentPwd, newPwd)); // 현재 비밀번호를 잘못 입력할 경우 예외를 던지는지 확인
        assertEquals(e.getErrorCode(), ErrorCode.INVALID_PASSWORD); // 해당 예외의 에러코드 확인
    }

    @Test
    @DisplayName("비밀번호 변경 실패 - 새 비밀번호가 현재 비밀번호와 일치")
    void updatePwdFailWithSamePwd() {
        // given
        Long userId = 1L;
        String userName = "testName";
        String currentPwd = "oldPwd";
        String newPwd = "oldPwd";
        String email = "test@test.com";
        String encodedOldPwd = "encodedOldPassword";
        Long teamId = 1L;
        UserEntity newUser = new UserEntity();
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setPassword(encodedOldPwd);
        newUser.setEmail(email);
        newUser.setTeamId(teamId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(newUser));
        when(bCryptPasswordEncoder.matches(currentPwd, encodedOldPwd)).thenReturn(true);
        when(bCryptPasswordEncoder.matches(newPwd, encodedOldPwd)).thenReturn(true);

        CommonException e = assertThrows(CommonException.class,
                () -> userCommandService.updatePassword(userId, currentPwd, newPwd));
        log.info("ErrorCode: {}", e.getErrorCode());
        assertTrue(e.getErrorCode().equals(ErrorCode.EXIST_PASSWORD));

        verify(bCryptPasswordEncoder, times(2)).matches(anyString(), anyString()); // 2번 호출되었는지 검증
        verify(userRepository, never()).save(any(UserEntity.class)); // 비밀번호가 수정된 적 없음을 검증
    }
}