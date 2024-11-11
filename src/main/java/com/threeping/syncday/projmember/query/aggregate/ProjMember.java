package com.threeping.syncday.projmember.query.aggregate;

import lombok.Data;

@Data
public class ProjMember {
    private Long userId;
    private Long projId;
    private String bookmarkStatus;
    private String participationStatus;
    private String role;
}
