package com.threeping.syncday.scheduleparticipant.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleParticipantNotificationDTO {

    @JsonProperty("schedule_id")
    Long scheduleId;

    @JsonProperty("user_id")
    Long userId;
    
    @JsonProperty("notification_time")
    Timestamp notificationTime;
}
