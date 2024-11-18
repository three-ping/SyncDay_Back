package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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
    private List<Long> participants;
}
