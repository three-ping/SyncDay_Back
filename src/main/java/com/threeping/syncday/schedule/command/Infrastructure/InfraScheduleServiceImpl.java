package com.threeping.syncday.schedule.command.Infrastructure;

import com.threeping.syncday.scheduleparticipant.command.application.service.AppScheduleParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InfraScheduleServiceImpl implements InfraScheduleService{

    private final AppScheduleParticipantService appScheduleParticipantService;

    @Autowired
    public InfraScheduleServiceImpl(AppScheduleParticipantService appScheduleParticipantService) {
        this.appScheduleParticipantService = appScheduleParticipantService;
    }

    @Override
    public void requestAddScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds, Timestamp notificationTime) {
        appScheduleParticipantService.addScheduleParticipant(userId, scheduleId, attendeeIds, notificationTime);
    }

    @Override
    public void requestUpdateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds) {
        appScheduleParticipantService.updateScheduleParticipant(userId, scheduleId, attendeeIds);
    }
}
