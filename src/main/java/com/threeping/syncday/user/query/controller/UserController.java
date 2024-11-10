package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.domain.vo.RegistRequestVO;
import com.threeping.syncday.user.query.dto.UserDTO;
import com.threeping.syncday.user.query.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health")
    public String health(){
        return "running on localhost:5000";
    }

    @PostMapping("/regist")
    public ResponseDTO<?> registNewUser(@RequestBody RegistRequestVO requestVO) throws ParseException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO newUser = modelMapper.map(requestVO, UserDTO.class);
        userService.registUser(newUser);

        return ResponseDTO.ok(newUser);
    }
    
}
