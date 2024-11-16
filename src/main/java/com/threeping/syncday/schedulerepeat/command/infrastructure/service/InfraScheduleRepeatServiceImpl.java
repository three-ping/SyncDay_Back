package com.threeping.syncday.schedulerepeat.command.infrastructure.service;

import com.threeping.syncday.schedule.command.application.service.AppScheduleService;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraScheduleRepeatServiceImpl implements InfraScheduleRepeatService {

    private final AppScheduleService appScheduleService;

    @Autowired
    InfraScheduleRepeatServiceImpl(AppScheduleService appScheduleService){
        this.appScheduleService = appScheduleService;
    }

    @Override
    public void createRepeatedSchedule(CreateRepeatedScheduleDTO createRepeatedScheduleDTO){

    }
}
