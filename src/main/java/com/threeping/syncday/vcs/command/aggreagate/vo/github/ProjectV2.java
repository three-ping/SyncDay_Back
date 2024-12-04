package com.threeping.syncday.vcs.command.aggreagate.vo.github;

import java.time.Instant;

public record ProjectV2(String id,
                        String title,
                        String description,
                        int number,
                        String url,
                        boolean closed,
                        Instant createdAt,
                        Instant updatedAt) {
}
