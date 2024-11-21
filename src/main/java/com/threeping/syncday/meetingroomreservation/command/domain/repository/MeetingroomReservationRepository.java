package com.threeping.syncday.meetingroomreservation.command.domain.repository;

import com.threeping.syncday.meetingroomreservation.command.aggregate.entity.MeetingroomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingroomReservationRepository extends JpaRepository<MeetingroomReservation, Long> {

    List<MeetingroomReservation> findBySchedule(Long scheduleId);

}
