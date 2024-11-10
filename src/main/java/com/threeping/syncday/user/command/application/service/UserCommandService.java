package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.user.query.dto.UserDTO;

public interface UserCommandService {
    void registUser(UserDTO newUser);
}
