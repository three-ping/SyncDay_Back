package com.threeping.syncday.schedule.query.service;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> getMySchedulesByUserId(Long userId);

    List<ScheduleDTO> getOthersSchedulesBySearchUserId(Long searchUserId);

    List<ScheduleDTO> getMyDetailSchedulesByUserIdAndScheduleId(Long userId, Long scheduleId);
}
