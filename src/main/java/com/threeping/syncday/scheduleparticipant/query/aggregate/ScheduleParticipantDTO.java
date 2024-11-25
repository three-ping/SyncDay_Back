package com.threeping.syncday.scheduleparticipant.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ParticipationStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleParticipantDTO {

    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("schedule_id")
    Long scheduleId;

    @JsonProperty("participation_status")
    ParticipationStatus participationStatus;

    @JsonProperty("notification_time")
    Timestamp notificationTime;
}
