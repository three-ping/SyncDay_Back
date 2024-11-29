package com.threeping.syncday.cardboard.command.aggreate.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.cardboard.command.aggreate.entity.VcsType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardboardDTO {

    @JsonProperty("cardboard_id")
    private Long cardboardId;

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
    private VcsType vcsType;

    @JsonProperty("vcs_milestone_url")
    private String vcsMilestoneUrl;

    @JsonProperty("workspace_id")
    private Long workspaceId;


}
