package com.threeping.syncday.schedule.query.service;

import com.threeping.syncday.schedule.query.repository.ScheduleMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ModelMapper modelMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, ModelMapper modelMapper) {
        this.scheduleMapper = scheduleMapper;
        this.modelMapper = modelMapper;
    }

}
