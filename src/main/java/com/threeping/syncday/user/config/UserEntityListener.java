package com.threeping.syncday.user.config;

import com.threeping.syncday.user.command.application.dto.UserEventDTO;
import com.threeping.syncday.user.query.dto.UserQueryDTO;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.query.repository.UserMapper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserEntityListener {
    private final ApplicationEventPublisher eventPublisher;
    private final UserMapper userMapper;

    @Autowired
    public UserEntityListener(ApplicationEventPublisher eventPublisher, UserMapper userMapper) {
        this.eventPublisher = eventPublisher;
        this.userMapper = userMapper;
    }

    @PostPersist  // Entity가 생성되는 Event 발생 시
    @PostUpdate  // Entity가 수정되는 Event 발생 시
    public void onPostPersistOrUpdate(UserEntity user) {
        // 1. user가 저장/수정된 후 추가 정보(team_name 등)을 조회
        UserQueryDTO userInfo = userMapper.findUserWithTeamName(user.getUserId());

        // 2. ES에 저장하기 위한 이벤트 발행
        eventPublisher.publishEvent(UserEventDTO.from(userInfo));
    }
}
