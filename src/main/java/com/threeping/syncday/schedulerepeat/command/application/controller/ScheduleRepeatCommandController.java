package com.threeping.syncday.schedulerepeat.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.application.service.ScheduleRepeatCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule-repeat")
public class ScheduleRepeatCommandController {

    private final ScheduleRepeatCommandService scheduleRepeatCommandService;

    @Autowired
    ScheduleRepeatCommandController(ScheduleRepeatCommandService scheduleRepeatCommandService){
        this.scheduleRepeatCommandService = scheduleRepeatCommandService;
    }

    @PostMapping("")
    private ResponseDTO<?> createScheduleRepeat(@RequestBody CreateScheduleRepeatDTO createScheduleRepeatDTO){
        scheduleRepeatCommandService.createScheduleRepeat(createScheduleRepeatDTO);
        return ResponseDTO.ok(null);
    }

}
