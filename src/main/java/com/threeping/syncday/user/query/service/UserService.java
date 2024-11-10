package com.threeping.syncday.user.query.service;

import com.threeping.syncday.user.query.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;

public interface UserService extends UserDetailsService {

    void registUser(UserDTO userDTO) throws ParseException;


}
