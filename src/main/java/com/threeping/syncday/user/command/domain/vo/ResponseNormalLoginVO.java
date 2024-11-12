package com.threeping.syncday.user.command.domain.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseNormalLoginVO {

    @JsonProperty("user_id")
    @Schema(description = "유저 고유번호(PK)", example = "1")
    private Long userId;

    @JsonProperty("username")
    @Schema(description = "유저 이름", example = "김정모")
    private String userName;

    @JsonProperty("email")
    @Schema(description = "유저 이메일", example = "syncday1211@gmail.com")
    private String email;

    @JsonProperty("profilephoto")
    @Schema(description = "유저 프로필 사진", example = "dachshund.jpg")
    private String profilePhoto;

    @JsonProperty("joinyear")
    @Schema(description = "입사연도", example = "20230120")
    private String joinYear;

    @JsonProperty("position")
    @Schema(description = "직책", example = "대리")
    private String position;

    @JsonProperty("teamid")
    @Schema(description = "팀번호", example = "1")
    private Long teamId;

}
