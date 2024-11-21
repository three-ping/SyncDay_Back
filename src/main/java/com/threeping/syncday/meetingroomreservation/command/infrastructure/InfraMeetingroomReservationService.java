package com.threeping.syncday.meetingroomreservation.command.infrastructure;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;

public interface InfraMeetingroomReservationService {

    ScheduleDTO requestAddSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO requestDeleteSchedule(Long scheduleId);
}
