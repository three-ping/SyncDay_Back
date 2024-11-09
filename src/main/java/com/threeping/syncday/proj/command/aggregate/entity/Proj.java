package com.threeping.syncday.proj.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="TBL_PROJ")
@Data
public class Proj {
    @Id
    @Column(name="proj_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projId;

    @Column(name="proj_name")
    private String projName;

    @Column(name="start_time")
    private Timestamp startTime;

    @Column(name="end_time")
    private Timestamp endTime;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="progress_status")
    private Byte progressStatus;

    @Column(name="user_id")
    private Long userId;


}
