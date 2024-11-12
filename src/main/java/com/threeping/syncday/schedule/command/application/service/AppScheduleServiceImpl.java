package com.threeping.syncday.schedule.command.application.service;

import com.threeping.syncday.schedule.command.domain.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppScheduleServiceImpl implements AppScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppScheduleServiceImpl(ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

}
