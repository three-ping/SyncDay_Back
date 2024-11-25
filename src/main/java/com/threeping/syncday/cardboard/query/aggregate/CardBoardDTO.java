package com.threeping.syncday.cardboard.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardBoardDTO {

    @JsonProperty("card_board_id")
    private String cardBoardId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("start_time")
    private Timestamp startTime;

    @JsonProperty("end_time")
    private Timestamp endTime;

    @JsonProperty("progress_status")
    private Byte progressStatus;

    @JsonProperty("vcs_type")
    private String vcsType;

    @JsonProperty("vcs_milestone_url")
    private String vcsMilestoneUrl;

    @JsonProperty("workspace_id")
    private String workspaceId;

}
