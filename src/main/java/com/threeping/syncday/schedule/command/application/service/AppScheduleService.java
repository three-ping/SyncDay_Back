package com.threeping.syncday.schedule.command.application.service;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;

public interface AppScheduleService {
    ScheduleDTO addSchedule(ScheduleDTO newSchedule, Long userId);

    ScheduleDTO modifySchedule(ScheduleDTO newSchedule, Long userId, Long scheduleId);

    ScheduleDTO deleteSchedule(Long scheduleId);
}
