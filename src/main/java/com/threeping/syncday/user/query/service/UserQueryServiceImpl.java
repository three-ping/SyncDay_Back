package com.threeping.syncday.user.query.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.command.domain.aggregate.UserEntity;
import com.threeping.syncday.user.command.application.dto.UserDTO;
import com.threeping.syncday.user.query.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("userQueryService")
@Slf4j
class UserQueryServiceImpl implements UserQueryService {

    private final UserMapper userMapper;

    @Autowired
    public UserQueryServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        UserEntity existingUser = userMapper.findByEmail(username);

        if (existingUser == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        String encodedPassword = existingUser.getPassword();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 우선 임의로 회원 권한 모두 USER로 설정. 피드백 받은 후 따로 권한 컬럼을 넣어서 세분화할 예정
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(existingUser.getEmail(),
                encodedPassword,
                true,
                true,
                true,
                true,
                grantedAuthorities);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByEmail(String email) {
        UserEntity user = userMapper.findByEmail(email);

        if (user == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_USER);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setProfilePhoto(user.getProfilePhoto());
        userDTO.setPosition(user.getPosition());
        userDTO.setJoinYear(timeStampToString(user.getJoinYear()));
        userDTO.setTeamId(user.getTeamId());

        return userDTO;
    }

    private String timeStampToString(Timestamp joinYear) {
        return joinYear.toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}