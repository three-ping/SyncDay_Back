package com.threeping.syncday.scheduleparticipant.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ParticipationStatus;
import lombok.Data;

@Data
public class ScheduleParticipantStatusDTO {

    @JsonProperty("schedule_id")
    Long scheduleId;

    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("participation_status")
    ParticipationStatus participationStatus;
}
