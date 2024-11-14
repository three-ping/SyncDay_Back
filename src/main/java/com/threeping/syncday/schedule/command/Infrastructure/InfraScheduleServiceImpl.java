package com.threeping.syncday.schedule.command.Infrastructure;

import com.threeping.syncday.scheduleparticipant.command.application.service.AppScheduleParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfraScheduleServiceImpl implements InfraScheduleService{

    private final AppScheduleParticipantService appScheduleParticipantService;

    public InfraScheduleServiceImpl(AppScheduleParticipantService appScheduleParticipantService) {
        this.appScheduleParticipantService = appScheduleParticipantService;
    }

    @Override
    public void requestAddScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds) {
        appScheduleParticipantService.addScheduleParticipant(userId, scheduleId, attendeeIds);
    }

    @Override
    public void requestUpdateScheduleParticipant(Long scheduleId, List<Long> attendeeIds) {
        appScheduleParticipantService.updateScheduleParticipant(scheduleId, attendeeIds);
    }
}
