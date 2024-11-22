package com.threeping.syncday.user.command.domain.repository;

import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);

    // 채팅방 멤버 찾기
//    Optional<UserEntity> findByUserId(List<Long> userIds);
}
