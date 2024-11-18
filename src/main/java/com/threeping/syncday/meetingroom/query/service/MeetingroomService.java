package com.threeping.syncday.meetingroom.query.service;

import com.threeping.syncday.meetingroom.query.aggregate.MeetingroomDTO;

import java.util.List;

public interface MeetingroomService {
    List<MeetingroomDTO> getAllMeetingrooms();

}
