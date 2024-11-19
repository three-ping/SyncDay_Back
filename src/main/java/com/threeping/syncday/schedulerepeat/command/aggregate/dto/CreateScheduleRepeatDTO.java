package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.PersonalMonthlyType;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.PersonalRecurrenceUnit;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.RecurrenceType;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.MeetingStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.PublicStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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
    private RecurrenceType recurrenceType;
    private PersonalRecurrenceUnit personalRecurrenceUnit;
    private Long personalRecurrenceInterval;
    private Long personalRecurrenceSelectedDays;
    private PersonalMonthlyType personalMonthlyType;
    private Timestamp repeatEnd;
    private Long userId;
    private String userName;
    private List<Long> participants;
    private List<String> participantsEmails;
}
