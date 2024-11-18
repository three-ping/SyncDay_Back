package com.threeping.syncday.schedule.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.schedule.query.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/my")
    public ResponseDTO<?> getMySchedules(@RequestParam Long userId) {
        return ResponseDTO.ok(scheduleService.getMySchedulesByUserId(userId));
    }

    @GetMapping("/others")
    public ResponseDTO<?> getOthersSchedules(@RequestParam Long searchUserId) {
        return ResponseDTO.ok(scheduleService.getOthersSchedulesBySearchUserId(searchUserId));
    }
}
