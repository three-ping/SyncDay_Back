package com.threeping.syncday.schedulerepeat.command.aggregate.entity;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.PersonalMonthlyType;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.PersonalRecurrenceUnit;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.RecurrenceType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TBL_SCHEDULE_REPEAT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @Enumerated(EnumType.STRING)
    @Column(name = "public_status")
    private PublicStatus publicStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_status")
    private MeetingStatus meetingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence_type")
    private RecurrenceType recurrenceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "personal_recurrence_unit")
    private PersonalRecurrenceUnit personalRecurrenceUnit;

    @Column(name = "personal_recurrence_interval")
    private Long personalRecurrenceInterval;

    @Column(name = "personal_recurrence_selected_days")
    private Long personalRecurrenceSelectedDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "personal_monthly_type")
    private PersonalMonthlyType personalMonthlyType;

    @Column(name = "repeat_end")
    private Timestamp repeatEnd;

    @Column(name = "user_id")
    private Long userId;
}
