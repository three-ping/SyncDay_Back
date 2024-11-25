package com.threeping.syncday.notification.infrastructure;

import com.threeping.syncday.schedule.query.aggregate.ScheduleDetailDTO;
import com.threeping.syncday.schedule.query.service.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class InfraNotificationServiceImpl implements InfraNotificationService{
    private final ScheduleService scheduleService;

    public InfraNotificationServiceImpl(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @Override
    public ScheduleDetailDTO findScheduleById(Long userId, Long scheduleId){
        return scheduleService.getMyDetailSchedulesByUserIdAndScheduleId(userId,scheduleId).get(0);
    }
}
