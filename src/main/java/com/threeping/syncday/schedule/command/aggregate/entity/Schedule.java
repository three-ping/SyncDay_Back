package com.threeping.syncday.schedule.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "TBL_SCHEDULE")
@Data
public class Schedule {
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "public_status")
    private PublicStatus publicStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeat_status")
    private RepeatStatus repeatStatus;

    @Column(name = "repeat_property")
    private String repeatProperty;

    @Column(name = "meetingroom_id")
    private Long meetingroomId;

    @Column(name = "user_id")
    private Long userID;

    /* 설명. 생성 시 updateTime에 현재 시각 설정 */
    @PrePersist
    public void prePersist() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
    }
}
