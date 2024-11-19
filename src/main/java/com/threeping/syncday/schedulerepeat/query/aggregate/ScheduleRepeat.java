package com.threeping.syncday.schedulerepeat.query.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ScheduleRepeat {
    private Long scheduleRepeatId;
    private String title;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp updateTime;
    private String meetingStatus;
    private String recurrencePattern;
    private Long userId;
}
