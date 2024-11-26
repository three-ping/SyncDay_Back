package com.threeping.syncday.meetingroomreservation.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.meetingroomreservation.command.aggregate.dto.MeetingroomReservationDTO;
import com.threeping.syncday.meetingroomreservation.command.application.service.AppMeetingroomReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/meetingroom_reservation")
public class AppMeetingroomReservationController {

    private final AppMeetingroomReservationService appMeetingroomReservationService;;

    @Autowired
    public AppMeetingroomReservationController(AppMeetingroomReservationService appMeetingroomReservationService) {
        this.appMeetingroomReservationService = appMeetingroomReservationService;
    }

    @PostMapping("")
    public ResponseDTO<?> createMeetingroomReservation(@RequestBody MeetingroomReservationDTO meetingroomReservationDTO) {
        log.info("new meetingroom reservation: {}", meetingroomReservationDTO);
        return ResponseDTO.ok(appMeetingroomReservationService.addMeetingroomReservation(meetingroomReservationDTO));
    }

    @DeleteMapping("/{schedule_id}")
    public ResponseDTO<?> deleteMeetingroomReservationByScheduleId(@PathVariable("schedule_id") Long scheduleId) {
        appMeetingroomReservationService.deleteMeetingroomReservationByScheduleId(scheduleId);
        return ResponseDTO.ok("Deleted reservations for schedule ID: " + scheduleId);
    }


}
