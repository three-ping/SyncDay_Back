package com.threeping.syncday.meetingroomreservation.query.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.meetingroomreservation.query.service.MeetingroomReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meetingroomreservation")
@Slf4j
public class MeetingroomReservationController {

    private MeetingroomReservationService meetingroomReservationService;

    @Autowired
    public MeetingroomReservationController(MeetingroomReservationService meetingroomReservationService) {
        this.meetingroomReservationService = meetingroomReservationService;
    }

    // 모든 회의실 예약 조회
    @GetMapping
    public ResponseDTO<?> findAllMeetingroomReservations() {
        return ResponseDTO.ok(meetingroomReservationService.getAllMeetingroomReservations());
    }
}
