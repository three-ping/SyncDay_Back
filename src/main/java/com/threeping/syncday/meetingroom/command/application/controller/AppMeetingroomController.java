package com.threeping.syncday.meetingroom.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.meetingroom.command.aggregate.dto.MeetingroomDTO;
import com.threeping.syncday.meetingroom.command.application.service.AppMeetingroomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/meetingroom")
public class AppMeetingroomController {

    private final AppMeetingroomServiceImpl appMeetingroomService;

    @Autowired
    public AppMeetingroomController(AppMeetingroomServiceImpl appMeetingroomService){
        this.appMeetingroomService = appMeetingroomService;
    }

    @PostMapping("/create")
    public ResponseDTO<?> createMeetingroom(@RequestBody MeetingroomDTO meetingroomDTO) {
        log.info("newMeetingroom: {}", meetingroomDTO);
        return ResponseDTO.ok(appMeetingroomService.addMeetingroom(meetingroomDTO));
    }

    @PutMapping("/update/{meetingroomId}")
    public ResponseDTO<?> updateMeetingroom(@PathVariable("meetingroomId") Long meetingroomId, @RequestBody MeetingroomDTO meetingroomDTO) {
        log.info("Updating meetingroom {}", meetingroomDTO);
        meetingroomDTO.setMeetingroomId(meetingroomId);
        return ResponseDTO.ok(appMeetingroomService.modifyMeetingroom(meetingroomDTO));
    }

    @DeleteMapping("/{meetingroomId}")
    public ResponseDTO<?> deleteMeetingroom(@PathVariable("meetingroomId") Long meetingroomId) {
        return ResponseDTO.ok(appMeetingroomService.deleteMeetingroom(meetingroomId));
    }

}
