package com.threeping.syncday.notification.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/sse")
@Slf4j
public class NotificationController {

    // 클라이언트별로 SseEmitter를 관리하는 Map
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * 클라이언트가 SSE를 구독할 때 호출되는 메서드
     *
     * @return SseEmitter 객체
     */
    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        // 고유한 클라이언트 ID 생성
        String clientId = UUID.randomUUID().toString();
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        // 클라이언트 추가
        emitters.put(clientId, emitter);
        log.info("Client [{}] subscribed, total clients: {}", clientId, emitters.size());

        // 클라이언트 연결 종료 시 콜백 설정
        emitter.onCompletion(() -> {
            emitters.remove(clientId);
            log.info("Client [{}] connection completed", clientId);
        });

        // 클라이언트 연결 타임아웃 시 콜백 설정
        emitter.onTimeout(() -> {
            emitters.remove(clientId);
            log.info("Client [{}] connection timed out", clientId);
        });

        return emitter;
    }

    /**
     * 모든 클라이언트에게 알림을 전송
     *
     * @param message 전송할 메시지
     */
    public void sendNotification(String message) {
        emitters.forEach((clientId, emitter) -> {
            try {
                emitter.send(SseEmitter.event().name("notification").data(message));
                log.info("Sent message to client [{}]: {}", clientId, message);
            } catch (IOException e) {
                // 클라이언트에 데이터 전송 실패 시 제거
                emitters.remove(clientId);
                log.warn("Error sending message to client [{}]: {}", clientId, e.getMessage());
            }
        });
    }
}
