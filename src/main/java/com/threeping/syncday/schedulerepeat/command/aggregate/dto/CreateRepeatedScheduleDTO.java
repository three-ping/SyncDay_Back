package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.MonthlyType;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.RecurrenceType;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.WeekDays;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateRepeatedScheduleDTO {
    private String title;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
    private PublicStatus publicStatus;
    private MeetingStatus meetingStatus;
    private Long userId;
    private Long scheduleRepeatId;
    private RecurrenceType recurrenceType;
    private Long recurrenceInterval;
    private WeekDays weekDays;
    private MonthlyType monthlyType;
}
