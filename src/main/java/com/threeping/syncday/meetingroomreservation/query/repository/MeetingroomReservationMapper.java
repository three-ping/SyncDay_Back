package com.threeping.syncday.meetingroomreservation.query.repository;

import com.threeping.syncday.meetingroomreservation.query.aggregate.MeetingroomReservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingroomReservationMapper {

    List<MeetingroomReservation> selectAllMeetingroomReservations();
}
