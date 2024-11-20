package com.threeping.syncday.schedule.command.application.service;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.aggregate.entity.Schedule;

public interface AppScheduleService {
    ScheduleDTO addSchedule(ScheduleDTO newSchedule);

    ScheduleDTO modifySchedule(ScheduleDTO newSchedule, Long scheduleId);

    ScheduleDTO deleteSchedule(Long scheduleId);

//    ScheduleDTO getScheduleById(Long scheduleId);
}
