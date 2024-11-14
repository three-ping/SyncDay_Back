package com.threeping.syncday.schedule.query.controller;

import com.threeping.syncday.schedule.query.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @GetMapping("/{userId}")
    void test(@RequestParam Long RequestUserId){}
}
