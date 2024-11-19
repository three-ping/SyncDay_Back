package com.threeping.syncday.schedulerepeat.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.application.service.ScheduleRepeatCommandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule-repeat")
@Slf4j
public class ScheduleRepeatCommandController {

    private final ScheduleRepeatCommandService scheduleRepeatCommandService;

    @Autowired
    ScheduleRepeatCommandController(ScheduleRepeatCommandService scheduleRepeatCommandService){
        this.scheduleRepeatCommandService = scheduleRepeatCommandService;
    }

    @Operation(summary = "일정반복 등록.",
            description = "일정 등록 시 반복 체크를 하면 일정반복 데이터를 저장하고, 참여자를 초대한 뒤, 반복일정을 생성합니다.")
    @PostMapping("")
    private ResponseDTO<?> createScheduleRepeat(@RequestBody CreateScheduleRepeatDTO createScheduleRepeatDTO){
        Long scheduleRepeatId = scheduleRepeatCommandService.createScheduleRepeat(createScheduleRepeatDTO);
        scheduleRepeatCommandService.createScheduleRepeatParticipants(scheduleRepeatId,createScheduleRepeatDTO);
        scheduleRepeatCommandService.sendMailToScheduleRepeatParticipants(scheduleRepeatId,createScheduleRepeatDTO);
        scheduleRepeatCommandService.createRepeatedSchedule(scheduleRepeatId,createScheduleRepeatDTO);
        return ResponseDTO.ok(null);
    }

}
