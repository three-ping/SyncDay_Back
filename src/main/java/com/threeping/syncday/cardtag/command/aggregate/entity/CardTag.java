package com.threeping.syncday.cardtag.command.aggregate.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_CARD_TAG")
public class CardTag {
    @Id
    @Column(name="tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name="tag_name")
    private String tagName;

    @Column(name="color")
    private String color;

    @Column(name="workspace_id")
    private Long WorkspaceId;
}
