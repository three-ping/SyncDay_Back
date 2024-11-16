package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import com.threeping.syncday.schedulerepeat.command.domain.repository.ScheduleRepeatRepository;
import com.threeping.syncday.schedulerepeat.command.domain.service.ScheduleRepeatDomainService;
import com.threeping.syncday.schedulerepeat.command.infrastructure.service.InfraScheduleRepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ScheduleRepeatCommandServiceImpl implements ScheduleRepeatCommandService {

    private final ScheduleRepeatRepository scheduleRepeatRepository;
    private final ScheduleRepeatDomainService scheduleRepeatDomainService;
    private final InfraScheduleRepeatService infraScheduleRepeatService;

    @Autowired
    ScheduleRepeatCommandServiceImpl(ScheduleRepeatRepository scheduleRepeatRepository,
                                     ScheduleRepeatDomainService scheduleRepeatDomainService,
                                     InfraScheduleRepeatService infraScheduleRepeatService){
        this.scheduleRepeatRepository = scheduleRepeatRepository;
        this.scheduleRepeatDomainService = scheduleRepeatDomainService;
        this.infraScheduleRepeatService = infraScheduleRepeatService;
    }

    @Override
    public Long createScheduleRepeat(CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        ScheduleRepeat scheduleRepeat = new ScheduleRepeat();
        createScheduleRepeatDtoToEntity(createScheduleRepeatDTO, scheduleRepeat);
        scheduleRepeatRepository.save(scheduleRepeat);

        return scheduleRepeat.getScheduleRepeatId();
    }

    @Override
    public void createRepeatedSchedule(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        CreateRepeatedScheduleDTO createRepeatedScheduleDTO = new CreateRepeatedScheduleDTO();
        scheduleRepeatDomainService.decodeRecurrencePattern(
                createScheduleRepeatDTO.getRecurrencePattern(),createRepeatedScheduleDTO);
        createRepeatedScheduleDTO.setScheduleRepeatId(scheduleRepeatId);




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
