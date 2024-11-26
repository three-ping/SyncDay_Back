package com.threeping.syncday.schedulerepeat.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.CreateScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.aggregate.dto.ScheduleRepeatDTO;
import com.threeping.syncday.schedulerepeat.command.application.service.ScheduleRepeatCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule-repeat")
@Slf4j
public class ScheduleRepeatCommandController {

    private final ScheduleRepeatCommandService scheduleRepeatCommandService;

    @Autowired
    ScheduleRepeatCommandController(ScheduleRepeatCommandService scheduleRepeatCommandService){
        this.scheduleRepeatCommandService = scheduleRepeatCommandService;
    }

    @Operation(summary = "일정반복 등록.",
            description = "일정 등록 시 반복 체크를 하면 일정반복 데이터를 저장하고, 참여자를 초대한 뒤, 반복일정을 생성합니다.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "일정반복 등록 성공",
                        content = @Content(schema = @Schema(implementation = ScheduleRepeatDTO.class))
                )
            }
    )
    @PostMapping("")
    private ResponseDTO<?> createScheduleRepeat(@RequestBody CreateScheduleRepeatDTO createScheduleRepeatDTO){
        ScheduleRepeatDTO scheduleRepeatDTO = scheduleRepeatCommandService.createScheduleRepeat(createScheduleRepeatDTO);
        Long scheduleRepeatId = scheduleRepeatDTO.getScheduleRepeatId();
        scheduleRepeatCommandService.createScheduleRepeatParticipants(scheduleRepeatId,createScheduleRepeatDTO);
        scheduleRepeatCommandService.createRepeatedSchedule(scheduleRepeatId,createScheduleRepeatDTO);
        scheduleRepeatCommandService.sendMailToScheduleRepeatParticipants(scheduleRepeatId,createScheduleRepeatDTO);
        return ResponseDTO.ok(scheduleRepeatDTO);
    }

    @Operation(summary = "일정반복 삭제.",
            description = "일정반복을 삭제하고, 일정반복 참여자를 삭제한 뒤, 반복일정들을 삭제합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "일정반복 삭제 성공",
                            content = @Content(schema = @Schema(implementation = ScheduleRepeatDTO.class))
                    )
            }
    )
    @DeleteMapping("/{scheduleRepeatId}")
    private ResponseDTO<?> deleteScheduleRepeat(@PathVariable Long scheduleRepeatId){
        scheduleRepeatCommandService.deleteScheduleRepeat(scheduleRepeatId);
        return ResponseDTO.ok(null);
    }

//    @PutMapping("{scheduleRepeatId}")
//    private ResponseDTO<?> updateAllScheduleRepeat(@PathVariable Long scheduleRepeatId,
//                                                @RequestBody CreateScheduleRepeatDTO CreateScheduleRepeatDTO) {
//        ScheduleRepeatDTO scheduleRepeatDTO = scheduleRepeatCommandService.updateAllScheduleRepeat(scheduleRepeatId,CreateScheduleRepeatDTO);
//        return ResponseDTO.ok(scheduleRepeatDTO);
//    }
//
//    @PutMapping("part/{scheduleRepeatId}")
//    private ResponseDTO<?> updatePartialScheduleRepeat(@PathVariable Long scheduleRepeatId,
//                                                       @RequestBody CreateScheduleRepeatDTO createScheduleRepeatDTO){
//        ScheduleRepeatDTO scheduleRepeatDTO = scheduleRepeatCommandService.updatePartialScheduleRepeat(scheduleRepeatId,createScheduleRepeatDTO);
//        return ResponseDTO.ok(scheduleRepeatDTO);
//    }

}
