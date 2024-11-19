package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.PersonalMonthlyType;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.PersonalRecurrenceUnit;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.RecurrenceType;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RepeatDTO {
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp repeatEnd;
    private RecurrenceType recurrenceType;
    private PersonalRecurrenceUnit personalRecurrenceUnit;
    private Long personalRecurrenceInterval;
    private Long personalRecurrenceSelectedDays;
    private PersonalMonthlyType personalMonthlyType;
}
