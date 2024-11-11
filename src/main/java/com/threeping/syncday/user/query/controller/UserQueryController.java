package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.query.dto.UserDTO;
import com.threeping.syncday.user.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserQueryController {

    private final UserQueryService userService;

    @Autowired
    public UserQueryController(UserQueryService userService) {
        this.userService = userService;
    }

    @GetMapping("/health")
    public String health(){
        return "running on localhost:5000";
    }

    @GetMapping("/profile")
    public ResponseDTO<?> findMyProfile(@AuthenticationPrincipal User user){
        String email = user.getUsername();
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseDTO.ok(userDTO);
    }
}
