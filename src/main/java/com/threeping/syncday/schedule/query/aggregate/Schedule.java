package com.threeping.syncday.schedule.query.aggregate;

import com.threeping.syncday.common.enumtype.MeetingStatus;
import com.threeping.syncday.common.enumtype.PublicStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Schedule {
    Long scheduleId;
    String title;
    String content;
    Timestamp startTime;
    Timestamp endTime;
    Timestamp updateTime;
    PublicStatus publicStatus;
    Long scheduleRepeatId;
    Long repeatOrder;
    MeetingStatus meetingStatus;
    Long meetingroomId;
    Long userId;
}
