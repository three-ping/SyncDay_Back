package com.threeping.syncday.teamcomment.query.aggregate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class CommentSearchResponse {
    @Schema(description = "댓글 고유 번호(PK)", example = "9", type = "Long")
    private Long commentId;
    @Schema(description = "댓글 내용", example = "다들 보셨다면 확인이라고 댓글을 달아주세요~")
    private String content;
    @Schema(description = "작성자 고유 번호(FK)", example = "2", type = "Long")
    private Long authorId;
    @Schema(description = "작성자 이름", example = "한지원")
    private String authorName;
    @Schema(description = "직위", example = "팀장")
    private String position;
    @Schema(description = "팀게시글 고유 번호(PK)", example = "9", type = "Long")
    private Long postId;
    @Schema(description = "팀게시글 제목", example = "현재 프로젝트 진행사항 관련 공지")
    private String postTitle;
    @Schema(description = "댓글 수정일자", example = "2024-11-11")
    private LocalDateTime updatedAt;
    @Schema(description = "댓글 생성일자", example = "2024-11-11")
    private LocalDateTime createdAt;
}
