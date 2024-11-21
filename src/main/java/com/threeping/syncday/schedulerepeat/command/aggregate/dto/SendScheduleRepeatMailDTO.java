package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.RecurrenceType;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SendScheduleRepeatMailDTO {
    private String title;
    private Timestamp startTime;
    private RecurrenceType recurrenceType;
    private String userName;
    private List<String> emails;
}
