package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.user.query.service.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/query/user")
public class UserQueryController {

    private final UserQueryService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserQueryController(UserQueryService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health")
    public String health(){
        return "running on localhost:5000";
    }
    
}
