package com.threeping.syncday.schedule.command.aggregate.entity;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
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
    @Column(name = "public_status", nullable = false)
    private PublicStatus publicStatus = PublicStatus.PRIVATE;

    @Column(name = "schedule_repeat_id")
    private Long scheduleRepeatId;

    @Column(name = "repeat_order")
    private Long repeatOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_status")
    private MeetingStatus meetingStatus;

    @Column(name = "meetingroom_id")
    private Long meetingroomId;

    @Column(name = "user_id")
    private Long userId;

    /* 설명. 생성 시 updateTime에 현재 시각 설정 */
    @PrePersist
    public void prePersist() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
    }
}
