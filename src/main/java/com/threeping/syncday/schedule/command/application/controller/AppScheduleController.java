package com.threeping.syncday.schedule.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.application.service.AppScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class AppScheduleController {
    private final AppScheduleService appScheduleService;

    @Autowired
    public AppScheduleController(AppScheduleService appScheduleService) {
        this.appScheduleService = appScheduleService;
    }

    @PostMapping("")
    public ResponseDTO<?> createSchedule(@RequestBody ScheduleDTO newSchedule,
                                         @RequestParam Long userId) {
        log.info("newSchedule: {}", newSchedule);
        return ResponseDTO.ok(appScheduleService.addSchedule(newSchedule, userId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseDTO<?> updateSchedule(@RequestBody ScheduleDTO newSchedule,
                                         @RequestParam Long userId,
                                         @PathVariable Long scheduleId) {
        log.info("ScheduleId: {}", scheduleId);
        log.info("newSchedule: {}", newSchedule);
        return ResponseDTO.ok(appScheduleService.modifySchedule(newSchedule, userId, scheduleId));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseDTO<?> deleteSchedule(@PathVariable Long scheduleId) {
        return ResponseDTO.ok(appScheduleService.deleteSchedule(scheduleId));
    }
}
