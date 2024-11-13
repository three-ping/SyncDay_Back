package com.threeping.syncday.schedulerepeat.command.aggregate.entity;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TBL_SCHEDULE_REPEAT")
public class ScheduleRepeat {
    @Id
    @Column(name = "schedule_repeat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleRepeatId;

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

    @Column(name = "public_status")
    private PublicStatus publicStatus;

    @Column(name = "meeting_status")
    private MeetingStatus meetingStatus;

    @Column(name = "recurrence_pattern")
    private String recurrencePattern;

    @Column(name = "user_id")
    private Long userId;
}
