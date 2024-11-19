package com.threeping.syncday.schedulerepeatparticipant.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;


public interface ScheduleRepeatParticipantService {
    void createScheduleRepeatParticipant(Long scheduleRepeatId,
                                         CreateScheduleRepeatDTO createScheduleRepeatDTO);
}
