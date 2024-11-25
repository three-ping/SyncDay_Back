package com.threeping.syncday.schedule.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.schedule.command.aggregate.entity.MeetingStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ScheduleDetailDTO {
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

    @JsonProperty("schedule_repeat_id")
    Long scheduleRepeatId;

    @JsonProperty("repeat_order")
    Long repeatOrder;

    @JsonProperty("meeting_status")
    MeetingStatus meetingStatus;

    @JsonProperty("meetingroom_id")
    Long meetingroomId;

    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("username")
    String username;

    // 사용자 정보 리스트
    @JsonProperty("user_info")
    List<UserInfoDTO> userInfo;
}
