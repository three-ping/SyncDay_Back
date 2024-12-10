package com.threeping.syncday.teamcomment.query.aggregate.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentQueryDTO {
    private Long commentId;
    private String content;
    private Long authorId;
    private String authorName;
    private String position;
    private Long postId;
    private String postTitle;
    private Timestamp updatedAt;
    private Timestamp createdAt;
}
