package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.ScheduleRepeatDTO;

public interface ScheduleRepeatCommandService {
    ScheduleRepeatDTO createScheduleRepeat(CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void createScheduleRepeatParticipants(Long scheduleRepeatId,
                                          CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void createRepeatedSchedule(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void sendMailToScheduleRepeatParticipants(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO);

//    ScheduleRepeatDTO updateAllScheduleRepeat(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO);
//
//    ScheduleRepeatDTO updatePartialScheduleRepeat(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void deleteScheduleRepeat(Long scheduleRepeatId);
}
