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
}
