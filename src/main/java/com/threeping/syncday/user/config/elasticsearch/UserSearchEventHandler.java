package com.threeping.syncday.user.config.elasticsearch;

import com.threeping.syncday.user.command.application.dto.UserEventDTO;
import com.threeping.syncday.user.infrastructure.elasticsearch.document.UserSearchDocument;
import com.threeping.syncday.user.infrastructure.elasticsearch.repository.UserSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserSearchEventHandler {

    private final UserSearchRepository userSearchRepository;

    @Autowired
    public UserSearchEventHandler(UserSearchRepository userSearchRepository) {
        this.userSearchRepository = userSearchRepository;
    }

    // userEntity의 수정/삭제 이벤트가 발생하면 ES 정보도 즉시 동기화하는 메서드
    @EventListener
    public void handleUserEvent(UserEventDTO dto) {
        UserSearchDocument document = UserSearchDocument
                .builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .email(dto.getEmail())
                .profileImage(dto.getProfileImage())
                .teamName(dto.getTeamName())
                .position(dto.getPosition())
                .build();

        userSearchRepository.save(document);
    }
}
