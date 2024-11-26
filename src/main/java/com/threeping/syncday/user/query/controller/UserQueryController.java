package com.threeping.syncday.user.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.command.application.dto.UserDTO;
import com.threeping.syncday.user.command.domain.vo.ResponseNormalLoginVO;
import com.threeping.syncday.user.query.dto.UserSearchResponse;
import com.threeping.syncday.user.query.service.UserQueryService;
import com.threeping.syncday.user.query.service.UserSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserQueryController {

    private final UserQueryService userService;
    private final UserSearchService userSearchService;

    @Autowired
    public UserQueryController(UserQueryService userService,
                               UserSearchService userSearchService) {
        this.userService = userService;
        this.userSearchService = userSearchService;
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
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseDTO.ok(userDTO);
    }

    @GetMapping("/refresh")
    public ResponseDTO<?> refresh(HttpServletRequest request){
        // at만 새로 발급받기 위한 end point
        return ResponseDTO.ok("accessToken 재발급 성공");
    }

    @Operation(summary = "회원 검색",
            description = "키워드로 회원을 검색합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원 검색 성공",
                            content = @Content(schema = @Schema(implementation = UserSearchResponse.class))
                    )
            }
    )
    @GetMapping("/search")
    public ResponseDTO<?> searchUser(@RequestParam String keyword){
        return ResponseDTO.ok(userSearchService.searchUser(keyword));
    }

    @Operation(summary = "회원 pk로 조회",
            description = "회원의 pk를 통해 UserDTO를 조회합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원 조회 성공",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @GetMapping("/{userId}")
    public ResponseDTO<?> findUserById(@PathVariable Long userId){
        return ResponseDTO.ok(userService.findById(userId));
    }
}
