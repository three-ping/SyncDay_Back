package com.threeping.syncday.schedule.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.schedule.command.Infrastructure.InfraScheduleService;
import com.threeping.syncday.schedule.command.aggregate.dto.ScheduleDTO;
import com.threeping.syncday.schedule.command.aggregate.entity.Schedule;
import com.threeping.syncday.schedule.command.domain.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Service
public class AppScheduleServiceImpl implements AppScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final InfraScheduleService infraScheduleService;

    @Autowired
    public AppScheduleServiceImpl(ScheduleRepository scheduleRepository, ModelMapper modelMapper, InfraScheduleService infraScheduleService) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.infraScheduleService = infraScheduleService;
    }

    @Transactional
    @Override
    public ScheduleDTO addSchedule(ScheduleDTO newScheduleDTO) {

        Schedule newSchedule = new Schedule();
        newSchedule.setTitle(newScheduleDTO.getTitle());
        newSchedule.setContent(newScheduleDTO.getContent());
        newSchedule.setStartTime(newScheduleDTO.getStartTime());
        newSchedule.setEndTime(newScheduleDTO.getEndTime());
        newSchedule.setUpdateTime(Timestamp.from(Instant.now()));
        newSchedule.setPublicStatus(newScheduleDTO.getPublicStatus());
        newSchedule.setScheduleRepeatId(newScheduleDTO.getScheduleRepeatId());
        newSchedule.setRepeatOrder(newScheduleDTO.getRepeatOrder());
        newSchedule.setMeetingStatus(newScheduleDTO.getMeetingStatus());
        newSchedule.setMeetingroomId(newScheduleDTO.getMeetingroomId());
        newSchedule.setUserId(newScheduleDTO.getUserId());

        scheduleRepository.saveAndFlush(newSchedule);

        if (newScheduleDTO.getAttendeeIds() != null) {
            infraScheduleService.requestAddScheduleParticipant(newSchedule.getUserId(), newSchedule.getScheduleId(), newScheduleDTO.getAttendeeIds());
        }
        // 참석자 추가 요청 (반복 생각은 아직 안함)

        return modelMapper.map(newSchedule, ScheduleDTO.class);
    }

    @Transactional
    @Override
    public ScheduleDTO modifySchedule(ScheduleDTO scheduleDTO, Long scheduleId) {

        Schedule newSchedule = scheduleRepository.findByScheduleId(scheduleId);
        if (newSchedule == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        newSchedule.setTitle(scheduleDTO.getTitle());
        newSchedule.setContent(scheduleDTO.getContent());
        newSchedule.setStartTime(scheduleDTO.getStartTime());
        newSchedule.setEndTime(scheduleDTO.getEndTime());
        newSchedule.setUpdateTime(Timestamp.from(Instant.now()));
        newSchedule.setPublicStatus(scheduleDTO.getPublicStatus());
        newSchedule.setScheduleRepeatId(scheduleDTO.getScheduleRepeatId());
        newSchedule.setRepeatOrder(scheduleDTO.getRepeatOrder());
        newSchedule.setMeetingStatus(scheduleDTO.getMeetingStatus());
        newSchedule.setMeetingroomId(scheduleDTO.getMeetingroomId());
        newSchedule.setUserId(scheduleDTO.getUserId());

        scheduleRepository.saveAndFlush(newSchedule);

        infraScheduleService.requestUpdateScheduleParticipant(newSchedule.getUserId(), newSchedule.getScheduleId(), scheduleDTO.getAttendeeIds());

        return modelMapper.map(newSchedule, ScheduleDTO.class);
    }

    @Transactional
    @Override
    public ScheduleDTO deleteSchedule(Long scheduleId) {

        Schedule newSchedule = scheduleRepository.findByScheduleId(scheduleId);
        if (newSchedule == null) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        scheduleRepository.delete(newSchedule);
        return modelMapper.map(newSchedule, ScheduleDTO.class);
    }
}
