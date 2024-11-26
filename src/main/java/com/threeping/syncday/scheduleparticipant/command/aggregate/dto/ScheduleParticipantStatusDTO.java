package com.threeping.syncday.scheduleparticipant.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.scheduleparticipant.command.aggregate.entity.ParticipationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScheduleParticipantStatusDTO {

    @JsonProperty("schedule_id")
    @Schema(description = "일정 고유번호", example = "1")
    Long scheduleId;

    @JsonProperty("user_id")
    @Schema(description = "유저 고유번호", example = "1")
    Long userId;

    @JsonProperty("participation_status")
    @Schema(description = "참여상태", example = "ATTEND")
    ParticipationStatus participationStatus;
}
