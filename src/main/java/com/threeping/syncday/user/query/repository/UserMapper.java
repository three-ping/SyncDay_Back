package com.threeping.syncday.user.query.repository;

import com.threeping.syncday.user.query.dto.UserQueryDTO;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserEntity findByEmail(String username);

    UserEntity findByUserId(Long userId);

    UserQueryDTO findUserWithTeamName(Long userId);

    List<UserQueryDTO> findAllUsersWithTeamName();
}
