package com.threeping.syncday.user.command.domain.repository;

import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
}
