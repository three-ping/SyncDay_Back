package com.threeping.syncday.schedule.query.aggregate;

import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.RepeatStatus;
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
    RepeatStatus repeatStatus;
    String repeatProperty;
    Long meetingroomId;
    Long userId;
}
