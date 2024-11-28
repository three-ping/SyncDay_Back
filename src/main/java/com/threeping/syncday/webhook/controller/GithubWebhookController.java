package com.threeping.syncday.webhook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook/github")
@Slf4j
public class GithubWebhookController {

    @PostMapping
    public ResponseEntity<?> handleWebhook(
            @RequestHeader(value = "X-GitHub-Event", required = false) String eventType,
            @RequestHeader(value = "X-Hub-Signature-256", required = false) String signature,
            @RequestBody String payload
    ) {
        log.debug("Event Type: {}", eventType);
        log.debug("Signature: {}", signature);
        log.debug("Payload: {}", payload);

        if (eventType == null) {
            log.error("Missing X-GitHub-Event header");
            return ResponseEntity.badRequest().body("Missing event type header");
        }

        return ResponseEntity.ok().build();
    }
}