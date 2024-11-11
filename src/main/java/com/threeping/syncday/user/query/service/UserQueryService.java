package com.threeping.syncday.user.query.service;

import com.threeping.syncday.user.query.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserQueryService extends UserDetailsService {
    UserDTO findByEmail(String email);

    UserDTO findByUserId(Long userId);
}
