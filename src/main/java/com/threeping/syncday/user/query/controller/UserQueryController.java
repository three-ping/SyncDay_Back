package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.application.dto.UserDTO;
import com.threeping.syncday.user.command.domain.vo.ResponseNormalLoginVO;
import com.threeping.syncday.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "마이페이지 조회",
            description = "회원이 Authentication Principal 객체를 통해 마이페이지를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "마이페이지 조회 성공",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @GetMapping("/profile")
    public ResponseDTO<?> findMyProfile(@AuthenticationPrincipal User user){
        String email = user.getUsername();
        UserDTO userDTO = userService.findByUserEmail(email);
        return ResponseDTO.ok(userDTO);
    }
}
