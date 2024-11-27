package com.threeping.syncday.cardcomment.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="tbl_card_comment")
@Data
public class CardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_comment_id")
    private Long cardCommentId;

    @Column(name="content")
    private String content;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column(name="user_id")
    private Long userId;

    @Column(name="card_id")
    private Long cardId;
}
