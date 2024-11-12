package com.threeping.syncday.schedule.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.RepeatStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleDTO {

    @JsonProperty("schedule_id")
    Long scheduleId;

    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    String content;

    @JsonProperty("start_time")
    Timestamp startTime;

    @JsonProperty("end_time")
    Timestamp endTime;

    @JsonProperty("update_time")
    Timestamp updateTime;

    @JsonProperty("public_status")
    PublicStatus publicStatus;

    @JsonProperty("repeat_status")
    RepeatStatus repeatStatus;

    @JsonProperty("repeat_property")
    String repeatProperty;

    @JsonProperty("meetingroom_id")
    Long meetingroomId;

    @JsonProperty("user_id")
    Long userID;
}
