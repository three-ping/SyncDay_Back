package com.threeping.syncday.notification.infrastructure;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import com.threeping.syncday.schedule.query.service.ScheduleService;
import com.threeping.syncday.scheduleparticipant.query.aggregate.ScheduleParticipantDTO;
import com.threeping.syncday.scheduleparticipant.query.service.ScheduleParticipantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InfraNotificationServiceImpl implements InfraNotificationService{
    private final ScheduleService scheduleService;
    private final ScheduleParticipantService scheduleParticipantService;

    public InfraNotificationServiceImpl(ScheduleService scheduleService,
                                        ScheduleParticipantService scheduleParticipantService){
        this.scheduleService = scheduleService;
        this.scheduleParticipantService = scheduleParticipantService;
    }

    @Override
    public ScheduleDTO findScheduleByScheduleId(Long scheduleId){
        log.info("foundSchedule: {}",scheduleService.getScheduleByScheduleId(scheduleId));
        return scheduleService.getScheduleByScheduleId(scheduleId);
    }

    @Override
    public List<ScheduleParticipantDTO> getParticipantsWithNotificationTimeInNext10Minutes() {
        return scheduleParticipantService.getParticipantsWithNotificationTimeInNext10Minutes();
    }
}
