package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.application.service.UserCommandService;
import com.threeping.syncday.user.command.domain.vo.RegistRequestVO;
import com.threeping.syncday.user.query.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserCommandController {

    private final UserCommandService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserCommandController(UserCommandService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/regist")
    public ResponseDTO<?> registNewUser(@RequestBody RegistRequestVO requestVO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO newUser = modelMapper.map(requestVO, UserDTO.class);
        userService.registUser(newUser);

        return ResponseDTO.ok(newUser);
    }
}
