package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.*;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleRepeatDTO {
    private Long scheduleRepeatId;
    private String title;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp updateTime;
    private PublicStatus publicStatus;
    private MeetingStatus meetingStatus;
    private RecurrenceType recurrenceType;
    private PersonalRecurrenceUnit personalRecurrenceUnit;
    private Long personalRecurrenceInterval;
    private Long personalRecurrenceSelectedDays;
    private PersonalMonthlyType personalMonthlyType;
    private Timestamp repeatEnd;
    private Long userId;
}
