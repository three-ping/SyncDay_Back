package com.threeping.syncday.notification.service;

import com.threeping.syncday.notification.infrastructure.InfraNotificationService;
import com.threeping.syncday.notification.redis.RedisNotificationStorage;
import com.threeping.syncday.schedule.query.aggregate.ScheduleDTO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final RedisNotificationStorage redisNotificationStorage;
    private final InfraNotificationService infraNotificationService;
    private final HashMap<Long, SseEmitter> emitters = new HashMap<>();
    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> heartbeatTask;

    @Autowired
    public NotificationServiceImpl(RedisNotificationStorage redisNotificationStorage, InfraNotificationService infraNotificationService) {
        this.redisNotificationStorage = redisNotificationStorage;
        this.infraNotificationService = infraNotificationService;
        log.info("service in this:{}",((Integer)System.identityHashCode(this)).toString());
    }

    @PostConstruct
    public void init() {
        taskScheduler.setPoolSize(200);
        taskScheduler.initialize();
        startHeartbeat();
    }

    @PreDestroy
    public void shutdown() {
        if (heartbeatTask != null) {
            heartbeatTask.cancel(true);
        }
        taskScheduler.shutdown();
    }

    @Override
    public SseEmitter createEmitter(Long userId) {
        log.info("service in this:{}",((Integer)System.identityHashCode(this)).toString());

        SseEmitter emitter = new SseEmitter(90000L);

        // 기존 Emitter가 있으면 완료 처리
        SseEmitter oldEmitter = emitters.put(userId, emitter);
        if (oldEmitter != null) {
            oldEmitter.complete();
        }
        // 연결 종료 및 타임아웃 시 Emitter 제거
        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));
        emitter.onError((e)->{
            emitters.remove(userId);
            log.warn("Emitter error for userId: {}",userId,e);
        });
        log.info("emitters: {}", emitters);

        return emitter;
    }

    @Override
    public void storeScheduleNotification(Long userId, Long scheduleId, Timestamp notificationTime){

        String redisKey = "schedule_notification:" + userId + ":" + scheduleId;
        String redisValue = userId + ":" + scheduleId;
        long ttlInSeconds = Duration
                .between(LocalDateTime.now(),notificationTime.toLocalDateTime())
                .getSeconds();
        log.info("now:{}, notice time: {}",LocalDateTime.now(),notificationTime.toLocalDateTime());
        if (ttlInSeconds > 0) {
            redisNotificationStorage.storeWithTTL(redisKey,redisValue,ttlInSeconds);
            log.info("Notification scheduled for user: '{}' with TTL of {} seconds", userId, ttlInSeconds);
        } else {
            log.warn("TTL for schedule ID '{}' is already expired or invalid.", scheduleId);
            log.warn("ttl: {}",ttlInSeconds);
        }

    }

    @Override
    public void sendScheduleNotification(Long userId, Long scheduleId) {
        log.info("in sendScheduleNotification emitters:{}",emitters);
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                ScheduleDTO scheduleDTO = infraNotificationService.findScheduleByScheduleId(scheduleId);
                emitter.send(SseEmitter.event().data(scheduleDTO));
                log.info("Notification sent to userId: {} for schedule: {}", userId, scheduleDTO.getTitle());
            } catch (Exception e) {
                log.warn("Failed to send notification to userId: {}", userId, e);
                emitters.remove(userId);
            }
        }
    }

    private void startHeartbeat() {
        heartbeatTask = taskScheduler.scheduleAtFixedRate(() -> {
            log.info("In heartbeat emitters: {}",emitters);
            emitters.forEach((userId, emitter) -> {
                try {
                    emitter.send(SseEmitter.event().comment("heartbeat")); // 빈 이벤트 전송
                    log.info("Heartbeat sent to userId: {}", userId);
                } catch (Exception e) {
                    log.warn("Failed to send heartbeat to userId: {}", userId);
                    emitters.remove(userId);
                }
            });
        }, Duration.ofSeconds(60));
    }

    public HashMap<Long, SseEmitter> getEmitters(){
        return this.emitters;
    }
}