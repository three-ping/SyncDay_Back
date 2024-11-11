package com.threeping.syncday.user.query.repository;

import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserEntity findByEmail(String username);
}
