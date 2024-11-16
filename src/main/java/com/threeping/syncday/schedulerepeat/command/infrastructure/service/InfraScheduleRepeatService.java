package com.threeping.syncday.schedulerepeat.command.infrastructure.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;

public interface InfraScheduleRepeatService {
    void createRepeatedSchedule(CreateRepeatedScheduleDTO createRepeatedScheduleDTO);
}
