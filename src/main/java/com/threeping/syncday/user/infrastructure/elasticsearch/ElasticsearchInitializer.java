package com.threeping.syncday.user.infrastructure.elasticsearch;

import com.threeping.syncday.user.infrastructure.elasticsearch.document.UserSearchDocument;
import com.threeping.syncday.user.infrastructure.elasticsearch.repository.UserSearchRepository;
import com.threeping.syncday.user.query.dto.UserQueryDTO;
import com.threeping.syncday.user.query.repository.UserMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("user_init")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchInitializer {
    private final UserMapper userMapper;
    private final UserSearchRepository userSearchRepository;

    @PostConstruct
    public void init() {
        log.info("유저 ES 동기화 시작!");
        synchronizeAll();
    }

    // 수동 동기화 메서드 추가
    public void synchronizeAll() {
        userSearchRepository.deleteAll();
//        log.info("ES db와 동기화를 위해 es db 모두 비우기");

        List<UserQueryDTO> users = userMapper.findAllUsersWithTeamName();
        log.info("db에서 저장된 유저 수: ", users.size());

                users.forEach(userDto -> {
                    UserSearchDocument document = UserSearchDocument.builder()
                            .userId(userDto.getUserId())
                            .name(userDto.getName())
                            .email(userDto.getEmail())
                            .profileImage(userDto.getProfileImage())
                            .teamName(userDto.getTeamName())
                            .position(userDto.getPosition())
                            .build();
                    userSearchRepository.save(document);
                });
    }
}