package com.threeping.syncday.teamcomment.command.aggregate.dto;

import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentQueryDTO;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
public class CommentEventDTO {
    private Long commentId;
    private String content;
    private Long authorId;
    private String authorName;
    private String position;
    private Long postId;
    private String postTitle;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private String eventType;


    public static CommentEventDTO from(CommentQueryDTO dto) {
        return CommentEventDTO
                .builder()
                .commentId(dto.getCommentId())
                .content(dto.getContent())
                .authorId(dto.getAuthorId())
                .authorName(dto.getAuthorName())
                .position(dto.getPosition())
                .postId(dto.getPostId())
                .postTitle(dto.getPostTitle())
                .updatedAt(convertToLocalDateTime(dto.getUpdatedAt()))
                .createdAt(convertToLocalDateTime(dto.getCreatedAt()))
                .eventType("CREATE")
                .build();
    }

    public static CommentEventDTO createDeleted(CommentQueryDTO dto) {
        return CommentEventDTO
                .builder()
                .commentId(dto.getCommentId())
                .content(dto.getContent())
                .authorId(dto.getAuthorId())
                .authorName(dto.getAuthorName())
                .position(dto.getPosition())
                .postId(dto.getPostId())
                .postTitle(dto.getPostTitle())
                .updatedAt(convertToLocalDateTime(dto.getUpdatedAt()))
                .createdAt(convertToLocalDateTime(dto.getCreatedAt()))
                .eventType("DELETE")
                .build();
    }

    private static LocalDateTime convertToLocalDateTime(Timestamp createdAt) {
        if (createdAt == null) {
            return null;
        }
        return createdAt.toLocalDateTime();
    }
}
