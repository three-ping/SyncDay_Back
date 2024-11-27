package com.threeping.syncday.cardboard.command.aggreate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tbl_cardboard")
public class CardBoard {
    @Id
    @Column(name="cardboard_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardboardId;

    @Column(name="title")
    private String title;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="start_time")
    private Timestamp startTime;

    @Column(name="end_time")
    private Timestamp endTime;

    @Column(name = "progress_status")
    private Byte progressStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="vcs_type")
    private VcsType vcsType;

    @Column(name="vcs_milestone_url")
    private String vcsMilestoneUrl;


    @Column(name="workspace_id")
    private Long workspaceId;

}
