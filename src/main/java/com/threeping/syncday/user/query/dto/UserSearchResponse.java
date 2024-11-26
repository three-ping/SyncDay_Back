package com.threeping.syncday.user.query.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserSearchResponse {
    @Schema(description = "유저 고유 번호(PK)", example = "11", type = "Long")
    private Long userId;
    @Schema(description = "유저 이름", example = "장그래")
    private String name;
    @Schema(description = "유저 이메일", example = "syncday1211@gmail.com")
    private String email;
    @Schema(description = "유저 프로필사진", example = "dachshund.jpg")
    private String profileImage;
    @Schema(description = "팀 이름", example = "개발팀")
    private String teamName;
    @Schema(description = "직책", example = "대리")
    private String position;
}
