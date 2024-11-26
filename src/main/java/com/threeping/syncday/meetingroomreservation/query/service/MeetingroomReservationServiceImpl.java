package com.threeping.syncday.meetingroomreservation.query.service;

import com.threeping.syncday.meetingroomreservation.query.aggregate.MeetingroomReservation;
import com.threeping.syncday.meetingroomreservation.query.aggregate.MeetingroomReservationDTO;
import com.threeping.syncday.meetingroomreservation.query.repository.MeetingroomReservationMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingroomReservationServiceImpl implements MeetingroomReservationService {

    private final MeetingroomReservationMapper meetingroomReservationMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public MeetingroomReservationServiceImpl(MeetingroomReservationMapper meetingroomReservationMapper, ModelMapper modelMapper) {
        this.meetingroomReservationMapper = meetingroomReservationMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MeetingroomReservationDTO> getAllMeetingroomReservations() {
        List<MeetingroomReservation> meetingroomReservations = meetingroomReservationMapper.selectAllMeetingroomReservations();
        List<MeetingroomReservationDTO> meetingroomReservationDTOs =
                meetingroomReservations.stream().map(meetingroomReservation -> modelMapper.map(meetingroomReservation, MeetingroomReservationDTO.class)).collect(Collectors.toList());
        return meetingroomReservationDTOs;
    }

    @Override
    public List<MeetingroomReservationDTO> getMeetingroomReservationsByRoomId(Long meetingRoomId) {
        // 특정 meetingRoomId로 조회 후 DTO로 변환
        List<MeetingroomReservation> meetingroomReservations = meetingroomReservationMapper.selectMeetingroomReservationsByRoomId(meetingRoomId);
        return meetingroomReservations.stream()
                .map(meetingroomReservation -> modelMapper.map(meetingroomReservation, MeetingroomReservationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingroomReservationDTO> getMeetingroomReservationsByPlace(String meetingRoomPlace) {
        List<MeetingroomReservation> reservations = meetingroomReservationMapper.selectMeetingroomReservationsByPlace(meetingRoomPlace);
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, MeetingroomReservationDTO.class))
                .collect(Collectors.toList());
    }
}
