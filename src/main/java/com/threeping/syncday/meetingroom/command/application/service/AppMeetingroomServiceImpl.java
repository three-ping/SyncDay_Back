package com.threeping.syncday.meetingroom.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.meetingroom.command.aggregate.dto.MeetingroomDTO;
import com.threeping.syncday.meetingroom.command.aggregate.entity.Meetingroom;
import com.threeping.syncday.meetingroom.command.domain.repository.MeetingroomRepository;
import com.threeping.syncday.team.command.domain.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AppMeetingroomServiceImpl implements AppMeetingroomService {

    private final MeetingroomRepository meetingroomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    public AppMeetingroomServiceImpl(MeetingroomRepository meetingroomRepository
                                   , ModelMapper modelMapper) {
        this.meetingroomRepository = meetingroomRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public MeetingroomDTO addMeetingroom(MeetingroomDTO meetingroomDTO) {
        Meetingroom meetingroom = modelMapper.map(meetingroomDTO, Meetingroom.class);
        log.info("meetingroom: {}", meetingroom);

        Meetingroom addedMeetingroom = meetingroomRepository.save(meetingroom);
        return modelMapper.map(addedMeetingroom, MeetingroomDTO.class);
    }

    @Transactional
    @Override
    public MeetingroomDTO modifyMeetingroom(MeetingroomDTO meetingroomDTO) {
        // 회의실 조회
        Meetingroom existingMeetingroom = meetingroomRepository.findById(meetingroomDTO.getMeetingroomId())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));

        // DTO의 값을 기존 엔티티에 업데이트
        existingMeetingroom.setMeetingroomPlace(meetingroomDTO.getMeetingroomPlace());
        existingMeetingroom.setMeetingroomName(meetingroomDTO.getMeetingroomName());
        existingMeetingroom.setMeetingroomCapacity(meetingroomDTO.getMeetingroomCapacity());

        log.info("existingMeetingroom: {}", existingMeetingroom);

        Meetingroom updatedMeetingroom = meetingroomRepository.save(existingMeetingroom);
        return modelMapper.map(updatedMeetingroom, MeetingroomDTO.class);
    }

@Transactional
@Override
public MeetingroomDTO deleteMeetingroom(Long meetingroomId) {
    // meetingroomId로 회의실 조회
    Meetingroom existingMeetingroom = meetingroomRepository.findByMeetingroomId(meetingroomId);
    if (existingMeetingroom == null) {
        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // 삭제 처리
    meetingroomRepository.delete(existingMeetingroom);
    return modelMapper.map(existingMeetingroom, MeetingroomDTO.class);
}
}
