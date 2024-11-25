package com.threeping.syncday.meetingroomreservation.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "TBL_MEETINGROOM_RESERVATION", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"meetingroom_id", "meeting_time"})
})
@Data
@NoArgsConstructor
public class MeetingroomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meetingroom_reservation_id")
    private Long meetingroomReservationId;

    // 회의시간 (10분 간격으로 저장)
    @Column(name="meeting_time", nullable=false)
    private Timestamp meetingTime;

    @Column(name = "meetingroom_id", nullable=false)
    private Long meetingRoom;

    @Column(name="schedule_id", nullable=false)
    private Long schedule;
}
