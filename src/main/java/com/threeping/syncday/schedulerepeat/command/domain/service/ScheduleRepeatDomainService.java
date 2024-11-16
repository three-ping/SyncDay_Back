package com.threeping.syncday.schedulerepeat.command.domain.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;

public interface ScheduleRepeatDomainService {
    void decodeRecurrencePattern(String recurrencePattern, CreateRepeatedScheduleDTO createRepeatedScheduleDTO);
}
