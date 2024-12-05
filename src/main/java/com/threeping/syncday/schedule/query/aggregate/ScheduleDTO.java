package com.threeping.syncday.schedule.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.schedule.command.aggregate.entity.MeetingStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Schema(name = "queryScheduleDTO")
public class ScheduleDTO {

    @JsonProperty("schedule_id")
    @Schema(description = "일정 고유번호", example = "1")
    Long scheduleId;

    @JsonProperty("title")
    @Schema(description = "제목", example = "결혼식")
    String title;

    @JsonProperty("start_time")
    @Schema(description = "시작날짜 및 시간", example = "2024-11-30T13:50:00.000Z")
    Timestamp startTime;

    @JsonProperty("end_time")
    @Schema(description = "종료날짜 및 시간", example = "2024-11-30T14:50:00.000Z")
    Timestamp endTime;

    @JsonProperty("meeting_status")
    @Schema(description = "회의 여부", example = "ACTIVE")
    MeetingStatus meetingStatus;

    @JsonProperty("status")
    @Schema(description = "사용자-일정 상태", example = "ACTIVE")
    String status;
}
