package com.threeping.syncday.schedulerepeat.command.aggregate.dto;

import com.threeping.syncday.schedulerepeat.command.aggregate.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleRepeatDTO {
    @Schema(description = "반복일정 고유번호", example = "1")
    private Long scheduleRepeatId;
    @Schema(description = "반복일정 제목", example = "매일 오전 조깅")
    private String title;
    @Schema(description = "반복일정 내용", example = "출근 전 한강 조깅하기")
    private String content;
    @Schema(description = "반복일정 중 첫 일정 시작 시각", example = "2024-11-26T09:00:00+09:00")
    private Timestamp startTime;
    @Schema(description = "반복일정 중 첫 일정 종료 시각", example = "2024-11-26T12:00:00+09:00")
    private Timestamp endTime;
    @Schema(description = "반복일정 데이터 수정 시각", example = "2024-11-24T09:00:00+09:00")
    private Timestamp updateTime;
    @Schema(description = "반복일정을 다른 사람이 조회했을 때 내용이 보이는 지 여부", example = "PUBLIC")
    private PublicStatus publicStatus;
    @Schema(description = "반복일정 회의 여부", example = "ACTIVE")
    private MeetingStatus meetingStatus;
    @Schema(description = "반복일정 반복 타입", example = "EVERYDAY")
    private RecurrenceType recurrenceType;
    @Schema(description = "사용자 지정 반복 단위", example = "WEEK")
    private PersonalRecurrenceUnit personalRecurrenceUnit;
    @Schema(description = "사용자 지정 반복 주기", example = "2")
    private Long personalRecurrenceInterval;
    @Schema(description = "사용자 지정 주간 반복 시 반복 요일(example: 0001010(2) 화/목 반복)", example = "10")
    private Long personalRecurrenceSelectedDays;
    @Schema(description = "사용자 지정 월간 반복 시 반복 타입", example = "EVERY_DAY")
    private PersonalMonthlyType personalMonthlyType;
    @Schema(description = "반복일정 반복 종료 시점", example = "2025-11-26T09:00:00+09:00")
    private Timestamp repeatEnd;
    @Schema(description = "유저 고유번호", example = "1")
    private Long userId;
}
