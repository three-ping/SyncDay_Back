package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;

public interface ScheduleRepeatCommandService {
    Long createScheduleRepeat(CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void createRepeatedSchedule(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO);
}
