package com.threeping.syncday.meetingroom.command.domain.repository;

import com.threeping.syncday.meetingroom.command.aggregate.entity.Meetingroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingroomRepository extends JpaRepository<Meetingroom, Long> {
    Meetingroom findByMeetingroomId(Long meetingroomId);
}
