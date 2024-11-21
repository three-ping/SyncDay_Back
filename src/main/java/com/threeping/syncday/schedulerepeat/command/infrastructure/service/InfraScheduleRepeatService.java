package com.threeping.syncday.schedulerepeat.command.infrastructure.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.SendScheduleRepeatMailDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;

public interface InfraScheduleRepeatService {
    void createSchedule(ScheduleDurationVO scheduleDuration,
                        CreateRepeatedScheduleDTO createRepeatedScheduleDTO,
                        Long repeatOrder);

    void createScheduleRepeatParticipants(Long repeatId,
                                          CreateScheduleRepeatDTO createScheduleRepeatDTO);

    void sendMailToRepeatScheduleParticipants(SendScheduleRepeatMailDTO createScheduleRepeatDTO);
}
