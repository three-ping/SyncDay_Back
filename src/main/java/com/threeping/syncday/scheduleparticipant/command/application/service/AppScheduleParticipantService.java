package com.threeping.syncday.scheduleparticipant.command.application.service;

import com.threeping.syncday.scheduleparticipant.command.aggregate.dto.ResponseScheduleParticipantDTO;
import com.threeping.syncday.scheduleparticipant.command.aggregate.dto.ScheduleParticipantNotificationDTO;
import com.threeping.syncday.scheduleparticipant.command.aggregate.dto.ScheduleParticipantStatusDTO;

import java.sql.Timestamp;
import java.util.List;

public interface AppScheduleParticipantService {
    void addScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds, Timestamp notificationTime);

    void updateScheduleParticipant(Long userId, Long scheduleId, List<Long> attendeeIds);

    void deleteScheduleParticipant(Long scheduleId);

    ResponseScheduleParticipantDTO updateUserScheduleStatus(ScheduleParticipantStatusDTO newScheduleParticipantStatus);

    ResponseScheduleParticipantDTO updateUserScheduleNotification(ScheduleParticipantNotificationDTO newScheduleParticipantNotification);
}
