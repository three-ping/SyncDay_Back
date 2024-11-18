package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateRepeatedScheduleDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.RepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;
import com.threeping.syncday.schedulerepeat.command.domain.repository.ScheduleRepeatRepository;
import com.threeping.syncday.schedulerepeat.command.domain.service.ScheduleRepeatDomainService;
import com.threeping.syncday.schedulerepeat.command.infrastructure.service.InfraScheduleRepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
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
    public void createScheduleRepeatParticipants(Long scheduleRepeatId,
                                                 CreateScheduleRepeatDTO createScheduleRepeatDTO){
        infraScheduleRepeatService.createScheduleRepeatParticipants(scheduleRepeatId,createScheduleRepeatDTO);
    }


    @Override
    public void createRepeatedSchedule(Long scheduleRepeatId, CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        CreateRepeatedScheduleDTO createRepeatedScheduleDTO = makeCreateRepeatedScheduleDTO(
                createScheduleRepeatDTO,scheduleRepeatId);
        RepeatDTO repeatDTO = makeRepeatDTO(createScheduleRepeatDTO);

        List<ScheduleDurationVO> repeatDays = scheduleRepeatDomainService.getRepeatDays(repeatDTO);
        if (repeatDays.isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
        Long repeatOrder = 1L;
        for (ScheduleDurationVO scheduleDuration: repeatDays){
            infraScheduleRepeatService.createSchedule(scheduleDuration,createRepeatedScheduleDTO,repeatOrder);
            repeatOrder++;
        }

    }


    private void createScheduleRepeatDtoToEntity(CreateScheduleRepeatDTO createScheduleRepeatDTO, ScheduleRepeat scheduleRepeat) {
        scheduleRepeat.setTitle(createScheduleRepeatDTO.getTitle());
        scheduleRepeat.setContent(createScheduleRepeatDTO.getContent());
        scheduleRepeat.setStartTime(createScheduleRepeatDTO.getStartTime());
        scheduleRepeat.setEndTime(createScheduleRepeatDTO.getEndTime());
        scheduleRepeat.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        scheduleRepeat.setPublicStatus(createScheduleRepeatDTO.getPublicStatus());
        scheduleRepeat.setMeetingStatus(createScheduleRepeatDTO.getMeetingStatus());
        scheduleRepeat.setRecurrenceType(createScheduleRepeatDTO.getRecurrenceType());
        scheduleRepeat.setPersonalRecurrenceUnit(createScheduleRepeatDTO.getPersonalRecurrenceUnit());
        scheduleRepeat.setPersonalRecurrenceInterval(createScheduleRepeatDTO.getPersonalRecurrenceInterval());
        scheduleRepeat.setPersonalRecurrenceSelectedDays(createScheduleRepeatDTO.getPersonalRecurrenceSelectedDays());
        scheduleRepeat.setPersonalMonthlyType(createScheduleRepeatDTO.getPersonalMonthlyType());
        scheduleRepeat.setUserId(createScheduleRepeatDTO.getUserId());
    }

    private CreateRepeatedScheduleDTO makeCreateRepeatedScheduleDTO(CreateScheduleRepeatDTO createScheduleRepeatDTO,
                                                                           Long scheduleRepeatId){
        CreateRepeatedScheduleDTO createRepeatedScheduleDTO = new CreateRepeatedScheduleDTO();
        createRepeatedScheduleDTO.setTitle(createScheduleRepeatDTO.getTitle());
        createRepeatedScheduleDTO.setContent(createScheduleRepeatDTO.getContent());
        createRepeatedScheduleDTO.setStartTime(createScheduleRepeatDTO.getStartTime());
        createRepeatedScheduleDTO.setEndTime(createScheduleRepeatDTO.getEndTime());
        createRepeatedScheduleDTO.setMeetingStatus(createScheduleRepeatDTO.getMeetingStatus());
        createRepeatedScheduleDTO.setPublicStatus(createScheduleRepeatDTO.getPublicStatus());
        createRepeatedScheduleDTO.setUserId(createScheduleRepeatDTO.getUserId());
        createRepeatedScheduleDTO.setScheduleRepeatId(scheduleRepeatId);
        createRepeatedScheduleDTO.setParticipants(createScheduleRepeatDTO.getParticipants());
        return createRepeatedScheduleDTO;
    }

    private RepeatDTO makeRepeatDTO(CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        RepeatDTO repeatDTO = new RepeatDTO();
        repeatDTO.setRecurrenceType(createScheduleRepeatDTO.getRecurrenceType());
        repeatDTO.setPersonalRecurrenceUnit(createScheduleRepeatDTO.getPersonalRecurrenceUnit());
        repeatDTO.setPersonalRecurrenceInterval(createScheduleRepeatDTO.getPersonalRecurrenceInterval());
        repeatDTO.setPersonalMonthlyType(createScheduleRepeatDTO.getPersonalMonthlyType());
        repeatDTO.setPersonalRecurrenceSelectedDays(createScheduleRepeatDTO.getPersonalRecurrenceSelectedDays());
        repeatDTO.setStartTime(createScheduleRepeatDTO.getStartTime());
        repeatDTO.setEndTime(createScheduleRepeatDTO.getEndTime());
        repeatDTO.setRepeatEnd(createScheduleRepeatDTO.getRepeatEnd());
        return repeatDTO;
    }


}
