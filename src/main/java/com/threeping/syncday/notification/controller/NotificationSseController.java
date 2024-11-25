package com.threeping.syncday.notification.controller;

import com.threeping.syncday.notification.infrastructure.InfraNotificationService;
import com.threeping.syncday.schedule.query.aggregate.ScheduleDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/sse/notification")
public class NotificationSseController {

    private final ConcurrentHashMap<String,SseEmitter> emitters = new ConcurrentHashMap<>();
    private final InfraNotificationService infraNotificationService;

    public NotificationSseController(InfraNotificationService infraNotificationService){
        this.infraNotificationService = infraNotificationService;
    }

    @GetMapping( value = "/subscribe/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String userId){
        SseEmitter emitter = new SseEmitter(0L);
        emitters.put(userId,emitter);

        emitter.onCompletion(()->emitters.remove(userId));
        log.info("SSE 연결 성공 / 유저아이디: {}",userId);
        emitter.onTimeout(()->emitters.remove(userId));

        return emitter;
    }

    public void sendScheduleNotification(Long userId, Long scheduleId){
        SseEmitter emitter = emitters.get(userId.toString());
        ScheduleDetailDTO scheduleDetailDTO = infraNotificationService.findScheduleById(userId,scheduleId);
        if (emitter != null){
            try {
                emitter.send(SseEmitter.event().name(scheduleDetailDTO.getTitle()).data(scheduleDetailDTO));
            } catch (Exception e) {
                emitters.remove(userId.toString());
            }
        }
    }



}
