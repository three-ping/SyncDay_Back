package com.threeping.syncday.meetingroom.query.repository;

import com.threeping.syncday.meetingroom.query.aggregate.Meetingroom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingroomMapper {

    List<Meetingroom> selectAllMeetingrooms();
}
