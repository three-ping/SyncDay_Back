package com.threeping.syncday.user.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.user.command.application.service.UserCommandService;
import com.threeping.syncday.user.command.domain.vo.PwdChangeRequestVO;
import com.threeping.syncday.user.command.domain.vo.RegistRequestVO;
import com.threeping.syncday.user.query.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


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

        UserDTO newUser = modelMapper.map(requestVO, UserDTO.class);
        userService.registUser(newUser);

        return ResponseDTO.ok(newUser);
    }

    // 비밀번호 수정은 현재 비밀번호, 바꿀 비밀번호, 바꿀 비밀번호 확인 총 3개를 입력함.
    // 바꿀 비밀번호와 바꿀 비밀번호 확인은 프론트에서 equals로 하고, 백으로 넘어올 값은 현재 비밀번호와 바꿀 비밀번호만 넘어옴.
    @PostMapping("/password/{userId}")
    public ResponseDTO<?> updatePassword(@PathVariable Long userId,
                                         @RequestBody PwdChangeRequestVO request){

        userService.updatePassword(userId, request.getCurrentPwd(), request.getNewPwd());

        return ResponseDTO.ok("비밀번호가 변경되었습니다.");
    }
}
