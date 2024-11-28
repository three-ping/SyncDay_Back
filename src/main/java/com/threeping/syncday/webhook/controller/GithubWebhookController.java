package com.threeping.syncday.webhook.controller;

import com.threeping.syncday.webhook.service.GithubEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/api/webhook/github")
@Slf4j
public class GithubWebhookController {
    private final GithubEventService githubEventService;

    @Value("${github.app.webhook.secret}")
    private String webhookSecret;

    @Autowired
    public GithubWebhookController(GithubEventService githubEventService) {
        this.githubEventService = githubEventService;
    }
    @PostMapping
    public ResponseEntity<?> handleWebhook(
            @RequestHeader("X-Hub-Signature-256") String signature,
            @RequestHeader("X-GitHub-Event") String event,
            @RequestBody String payload
    ) {
        // Verify webhook signature
        if (!isValidSignature(payload, signature)) {
            return ResponseEntity.badRequest().build();
        }

        log.info("Received GitHub event: {}", event);
        log.debug("Payload: {}", payload);

        // Process event
        processWebhookEvent(event, payload);

        return ResponseEntity.ok().build();
    }

    private boolean isValidSignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(webhookSecret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            String computedSignature = "sha256=" +
                    Hex.encodeHexString(mac.doFinal(payload.getBytes()));
            return computedSignature.equals(signature);
        } catch (Exception e) {
            log.error("Error validating webhook signature", e);
            return false;
        }
    }
    @Async
    void processWebhookEvent(String event, String payload) {
        // Handle different event types
    }
}
