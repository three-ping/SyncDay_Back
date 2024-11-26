package com.threeping.syncday.scheduleparticipant.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.scheduleparticipant.command.aggregate.dto.ScheduleParticipantNotificationDTO;
import com.threeping.syncday.scheduleparticipant.command.aggregate.dto.ScheduleParticipantStatusDTO;
import com.threeping.syncday.scheduleparticipant.command.application.service.AppScheduleParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/userschedule")
public class AppScheduleParticipantController {
    private final AppScheduleParticipantService appScheduleParticipantService;

    @Autowired
    public AppScheduleParticipantController(AppScheduleParticipantService appScheduleParticipantService) {
        this.appScheduleParticipantService = appScheduleParticipantService;
    }

    // 참여 상태 수정
    @Operation(summary = "일정 참여상태 수정",
            description = "일정의 참여상태를 수정합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일정 참여상태 수정 성공",
                            content = @Content(schema = @Schema(implementation = ScheduleParticipantStatusDTO.class))
                    )
            }
    )
    @PutMapping("/status")
    public ResponseDTO<?> updateUserScheduleStatus(
            @RequestBody ScheduleParticipantStatusDTO newScheduleParticipantStatus) {
        return ResponseDTO.ok(appScheduleParticipantService.updateUserScheduleStatus(newScheduleParticipantStatus));
    }

    // 알람 시각 수정
    @Operation(summary = "일정 알람시각 수정",
            description = "일정의 알람시각을 수정합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일정 알람시각 수정 성공",
                            content = @Content(schema = @Schema(implementation = ScheduleParticipantNotificationDTO.class))
                    )
            }
    )
    @PutMapping("/notification")
    public ResponseDTO<?> updateUserScheduleNotification(
            @RequestBody ScheduleParticipantNotificationDTO newScheduleParticipantNotification) {
        return ResponseDTO.ok(appScheduleParticipantService
                .updateUserScheduleNotification(newScheduleParticipantNotification));
    }
}
