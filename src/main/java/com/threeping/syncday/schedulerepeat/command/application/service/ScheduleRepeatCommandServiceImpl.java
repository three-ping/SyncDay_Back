package com.threeping.syncday.schedulerepeat.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.schedule.command.aggregate.entity.MeetingStatus;
import com.threeping.syncday.schedule.command.aggregate.entity.PublicStatus;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.*;
import com.threeping.syncday.schedulerepeat.command.aggregate.entity.ScheduleRepeat;
import com.threeping.syncday.schedulerepeat.command.aggregate.vo.ScheduleDurationVO;
import com.threeping.syncday.schedulerepeat.command.domain.repository.ScheduleRepeatRepository;
import com.threeping.syncday.schedulerepeat.command.domain.service.ScheduleRepeatDomainService;
import com.threeping.syncday.schedulerepeat.command.infrastructure.service.InfraScheduleRepeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
@Slf4j
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
    public ScheduleRepeatDTO createScheduleRepeat(CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        if (createScheduleRepeatDTO.getStartTime().after(createScheduleRepeatDTO.getEndTime())) {
            throw new CommonException(ErrorCode.INVALID_REPEAT_SCHEDULE_START_END);
        }
        ScheduleRepeat scheduleRepeat = new ScheduleRepeat();
        createScheduleRepeatDtoToEntity(createScheduleRepeatDTO, scheduleRepeat);
        scheduleRepeatRepository.save(scheduleRepeat);

        return entityToDTO(scheduleRepeat);
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

    @Override
    public void sendMailToScheduleRepeatParticipants(Long scheduleRepeatId,
                                                     CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        SendScheduleRepeatMailDTO sendScheduleRepeatMailDTO = new SendScheduleRepeatMailDTO();
        sendScheduleRepeatMailDTO.setRecurrenceType(createScheduleRepeatDTO.getRecurrenceType());
        sendScheduleRepeatMailDTO.setStartTime(createScheduleRepeatDTO.getStartTime());
        sendScheduleRepeatMailDTO.setTitle(createScheduleRepeatDTO.getTitle());
        sendScheduleRepeatMailDTO.setEmails(createScheduleRepeatDTO.getParticipantsEmails());
        sendScheduleRepeatMailDTO.setUserName(createScheduleRepeatDTO.getUserName());
        infraScheduleRepeatService.sendMailToRepeatScheduleParticipants(sendScheduleRepeatMailDTO);
    }

    @Override
    public ScheduleRepeatDTO updateAllScheduleRepeat(Long scheduleRepeatId,
                                                     CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        ScheduleRepeat scheduleRepeat = scheduleRepeatRepository.findById(scheduleRepeatId)
                .orElseThrow(()-> new CommonException(ErrorCode.INVALID_INPUT_VALUE));
        scheduleRepeatRepository.delete(scheduleRepeat);
        createScheduleRepeat(createScheduleRepeatDTO);

        return null;
    }

    @Override
    public ScheduleRepeatDTO updatePartialScheduleRepeat(Long scheduleRepeatId,
                                                         CreateScheduleRepeatDTO createScheduleRepeatDTO) {
        return null;
    }

    private ScheduleRepeat changeScheduleRepeat(Long scheduleRepeatId,
                                                  UpdateScheduleRepeatDTO updateScheduleRepeatDTO) {
        ScheduleRepeat scheduleRepeat = scheduleRepeatRepository.findById(scheduleRepeatId)
                .orElseThrow(()-> new CommonException(ErrorCode.INVALID_INPUT_VALUE));
        scheduleRepeat.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if(!updateScheduleRepeatDTO.getTitle().isBlank()){
            scheduleRepeat.setTitle(updateScheduleRepeatDTO.getTitle());
        }
        if(!updateScheduleRepeatDTO.getContent().isBlank()){
            scheduleRepeat.setContent(updateScheduleRepeatDTO.getContent());
        }
        if(updateScheduleRepeatDTO.getStartTime() != null){
            scheduleRepeat.setStartTime(updateScheduleRepeatDTO.getStartTime());
        }
        if(updateScheduleRepeatDTO.getEndTime() != null){
            scheduleRepeat.setEndTime(updateScheduleRepeatDTO.getEndTime());
        }
        if(updateScheduleRepeatDTO.getPublicStatus() != null){
            scheduleRepeat.setPublicStatus(updateScheduleRepeatDTO.getPublicStatus());
        }
        if(updateScheduleRepeatDTO.getMeetingStatus() != null){
            scheduleRepeat.setMeetingStatus(updateScheduleRepeatDTO.getMeetingStatus());
        }
        scheduleRepeatRepository.save(scheduleRepeat);


        return scheduleRepeat;
    }


    private void createScheduleRepeatDtoToEntity(CreateScheduleRepeatDTO createScheduleRepeatDTO, ScheduleRepeat scheduleRepeat) {
        scheduleRepeat.setTitle(createScheduleRepeatDTO.getTitle());
        scheduleRepeat.setContent(createScheduleRepeatDTO.getContent());
        scheduleRepeat.setStartTime(createScheduleRepeatDTO.getStartTime());
        scheduleRepeat.setEndTime(createScheduleRepeatDTO.getEndTime());
        scheduleRepeat.setUpdateTime(Timestamp.from(Instant.now()));
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
        createRepeatedScheduleDTO.setMeetingStatus(converMeetingStatus(createScheduleRepeatDTO.getMeetingStatus()));
        createRepeatedScheduleDTO.setPublicStatus(convertPublicStatus(createScheduleRepeatDTO.getPublicStatus()));
        createRepeatedScheduleDTO.setUserId(createScheduleRepeatDTO.getUserId());
        createRepeatedScheduleDTO.setScheduleRepeatId(scheduleRepeatId);
        createRepeatedScheduleDTO.setParticipants(createScheduleRepeatDTO.getParticipants());
        return createRepeatedScheduleDTO;
    }

    private PublicStatus convertPublicStatus(com.threeping.syncday.schedulerepeat.command.aggregate.entity.PublicStatus publicStatus) {
        if (publicStatus == com.threeping.syncday.schedulerepeat.command.aggregate.entity.PublicStatus.PUBLIC){
            return PublicStatus.PUBLIC;
        } else {
            return PublicStatus.PRIVATE;
        }

    }

    private MeetingStatus converMeetingStatus(com.threeping.syncday.schedulerepeat.command.aggregate.entity.MeetingStatus meetingStatus) {
        if (meetingStatus == com.threeping.syncday.schedulerepeat.command.aggregate.entity.MeetingStatus.ACTIVE){
            return MeetingStatus.ACTIVE;
        } else {
            return MeetingStatus.INACTIVE;
        }

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

    private ScheduleRepeatDTO entityToDTO(ScheduleRepeat scheduleRepeat){
        ScheduleRepeatDTO scheduleRepeatDTO = new ScheduleRepeatDTO();
        scheduleRepeatDTO.setTitle(scheduleRepeat.getTitle());
        scheduleRepeatDTO.setContent(scheduleRepeat.getContent());
        scheduleRepeatDTO.setStartTime(scheduleRepeat.getStartTime());
        scheduleRepeatDTO.setEndTime(scheduleRepeat.getEndTime());
        scheduleRepeatDTO.setUpdateTime(scheduleRepeat.getUpdateTime());
        scheduleRepeatDTO.setMeetingStatus(scheduleRepeat.getMeetingStatus());
        scheduleRepeatDTO.setPublicStatus(scheduleRepeat.getPublicStatus());
        scheduleRepeatDTO.setRecurrenceType(scheduleRepeat.getRecurrenceType());
        scheduleRepeatDTO.setPersonalRecurrenceUnit(scheduleRepeat.getPersonalRecurrenceUnit());
        scheduleRepeatDTO.setPersonalRecurrenceInterval(scheduleRepeat.getPersonalRecurrenceInterval());
        scheduleRepeatDTO.setPersonalRecurrenceSelectedDays(scheduleRepeat.getPersonalRecurrenceSelectedDays());
        scheduleRepeatDTO.setRepeatEnd(scheduleRepeat.getRepeatEnd());
        scheduleRepeatDTO.setScheduleRepeatId(scheduleRepeat.getScheduleRepeatId());
        scheduleRepeatDTO.setUserid(scheduleRepeat.getUserId());

        return scheduleRepeatDTO;
    }


}
