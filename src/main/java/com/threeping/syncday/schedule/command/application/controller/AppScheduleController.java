package com.threeping.syncday.schedule.command.application.controller;

import com.threeping.syncday.schedule.command.application.service.AppScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/schedule")
public class AppScheduleController {
    private final AppScheduleService appScheduleService;

    @Autowired
    public AppScheduleController(AppScheduleService appScheduleService) {
        this.appScheduleService = appScheduleService;
    }



}
