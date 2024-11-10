package com.threeping.syncday.user.query.repository;

import com.threeping.syncday.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
