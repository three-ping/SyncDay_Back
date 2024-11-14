package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import com.threeping.syncday.schedulerepeat.command.domain.repository.ScheduleRepeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ScheduleRepeatCommandServiceImpl implements ScheduleRepeatCommandService {

    private final ScheduleRepeatRepository scheduleRepeatRepository;

    @Autowired
    ScheduleRepeatCommandServiceImpl(ScheduleRepeatRepository scheduleRepeatRepository){
        this.scheduleRepeatRepository = scheduleRepeatRepository;
    }

    @Override
    public void createScheduleRepeat(CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        ScheduleRepeat scheduleRepeat = new ScheduleRepeat();
        createScheduleRepeatDtoToEntity(createScheduleRepeatDTO, scheduleRepeat);
        scheduleRepeatRepository.save(scheduleRepeat);
    }

    private static void createScheduleRepeatDtoToEntity(CreateScheduleRepeatDTO createScheduleRepeatDTO, ScheduleRepeat scheduleRepeat) {
        scheduleRepeat.setTitle(createScheduleRepeatDTO.getTitle());
        scheduleRepeat.setContent(createScheduleRepeatDTO.getContent());
        scheduleRepeat.setStartTime(createScheduleRepeatDTO.getStartTime());
        scheduleRepeat.setEndTime(createScheduleRepeatDTO.getEndTime());
        scheduleRepeat.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        scheduleRepeat.setPublicStatus(createScheduleRepeatDTO.getPublicStatus());
        scheduleRepeat.setMeetingStatus(createScheduleRepeatDTO.getMeetingStatus());
        scheduleRepeat.setRecurrencePattern(createScheduleRepeatDTO.getRecurrencePattern());
        scheduleRepeat.setUserId(createScheduleRepeatDTO.getUserId());
    }
}
