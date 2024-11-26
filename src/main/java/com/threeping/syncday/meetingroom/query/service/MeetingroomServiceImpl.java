package com.threeping.syncday.meetingroom.query.service;

import com.threeping.syncday.meetingroom.query.aggregate.Meetingroom;
import com.threeping.syncday.meetingroom.query.aggregate.MeetingroomDTO;
import com.threeping.syncday.meetingroom.query.repository.MeetingroomMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingroomServiceImpl implements MeetingroomService {

    private final MeetingroomMapper meetingroomMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public MeetingroomServiceImpl(MeetingroomMapper meetingroomMapper, ModelMapper modelMapper) {
        this.meetingroomMapper = meetingroomMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MeetingroomDTO> getAllMeetingrooms() {
        List<Meetingroom> meetingrooms = meetingroomMapper.selectAllMeetingrooms();
        List<MeetingroomDTO> meetingroomDTOs =
                meetingrooms.stream().map(meetingroom -> modelMapper.map(meetingroom, MeetingroomDTO.class)).collect(Collectors.toList());
        return meetingroomDTOs;
    }

    @Override
    public MeetingroomDTO getMeetingroomById(Long meetingroomId) {
        // Mapper를 사용하여 특정 회의실 조회
        Meetingroom meetingroom = meetingroomMapper.selectMeetingroomById(meetingroomId);

        // 조회된 회의실 정보가 없을 경우 예외 처리
        if (meetingroom == null) {
            throw new IllegalArgumentException("회의실을 찾을 수 없습니다. ID: " + meetingroomId);
       }

            // ModelMapper를 사용하여 Entity -> DTO 변환
            return modelMapper.map(meetingroom, MeetingroomDTO.class);

    }
}
