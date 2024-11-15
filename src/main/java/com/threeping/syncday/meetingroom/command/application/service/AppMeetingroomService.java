package com.threeping.syncday.meetingroom.command.application.service;

import com.threeping.syncday.meetingroom.command.aggregate.dto.MeetingroomDTO;

public interface AppMeetingroomService {

    MeetingroomDTO addMeetingroom(MeetingroomDTO meetingroomDTO);

    MeetingroomDTO modifyMeetingroom(MeetingroomDTO meetingroomDTO);

    MeetingroomDTO deleteMeetingroom(Long meetingroomId);
}
