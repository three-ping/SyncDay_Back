package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.enumtype.RecurrencePattern;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateScheduleRepeatDTO {
    private String title;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
    private PublicStatus publicStatus;
    private MeetingStatus meetingStatus;
    private RecurrencePattern recurrencePattern;
    private Long userId;
}
