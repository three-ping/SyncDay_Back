package com.threeping.syncday.cardbookmark.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="TBL_CARD_BOOKMARK")
public class CardBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_bookmark_id")
    private Long cardBookmarkId;


    @Column(name="user_id")
    private Long userId;

    @Column(name="card_id")
    private Long cardId;


}
