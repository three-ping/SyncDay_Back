package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.domain.repository.UserRepository;
import com.threeping.syncday.user.query.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service("userCommandService")
@Slf4j
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserCommandServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void registUser(UserDTO newUser) {
        // 이미 존재하는지 확인
        UserEntity existingUser = userRepository.findUserByEmail(newUser.getEmail());

        if (existingUser != null) {
            throw new CommonException(ErrorCode.EXIST_USER_ID);
        }

        Timestamp joinYear = convertStringToTimeStamp(newUser.getJoinYear());
        UserEntity user = new UserEntity();
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setJoinYear(joinYear);
        user.setProfilePhoto(newUser.getProfilePhoto());
        user.setPosition(newUser.getPosition());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setTeamId(newUser.getTeamId());
        log.info("새로 등록되는 유저정보 registUser: {}", user);

        userRepository.save(user);
    }

    private Timestamp convertStringToTimeStamp(String joinYear) {
        try {
            // 날짜만 파싱
            LocalDate date = LocalDate.parse(joinYear,
                    DateTimeFormatter.ofPattern("yyyyMMdd"));

            // 원하는 시간 설정 (예: 오전 9시)
            LocalDateTime dateTime = date.atTime(9, 0, 0);

            log.info("변환된 입사연도 LocalDateTime: {}", dateTime);
            return Timestamp.valueOf(dateTime);
        } catch (DateTimeParseException e) {
            log.error("날짜 변환 중 오류 발생: {}", joinYear, e);
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }
    }
}
