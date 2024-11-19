package com.threeping.syncday.meetingroomreservation.command.infrastructure;

import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.application.service.AppScheduleService;
import org.springframework.stereotype.Service;

@Service
public class InfraMeetingroomReservationServiceImpl implements InfraMeetingroomReservationService {

    private final AppScheduleService appScheduleService;

    public InfraMeetingroomReservationServiceImpl(AppScheduleService appScheduleService) {
        this.appScheduleService = appScheduleService;
    }

    @Override
    public ScheduleDTO requestAddSchedule(ScheduleDTO scheduleDTO){
        return appScheduleService.addSchedule(scheduleDTO);
    }

    @Override
    public ScheduleDTO requestDeleteSchedule(Long scheduleId){
        return appScheduleService.deleteSchedule(scheduleId);
    }
}
