package com.threeping.syncday.scheduleparticipant.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleParticipantNotificationDTO {

    @JsonProperty("schedule_id")
    @Schema(description = "일정 고유번호", example = "1")
    Long scheduleId;

    @JsonProperty("user_id")
    @Schema(description = "유저 고유번호", example = "1")
    Long userId;
    
    @JsonProperty("notification_time")
    @Schema(description = "알람시각", example = "2024-11-30 13:30:00")
    Timestamp notificationTime;
}
