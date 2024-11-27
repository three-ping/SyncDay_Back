package com.threeping.syncday.cardboard.command.aggreate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.cardboard.command.aggreate.entity.VcsType;
import lombok.Data;

@Data
public class AppCardboardVO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("cardboard_id")
    private Long cardboardId;

    @JsonProperty("vcs_type")
    private VcsType vcsType;

    @JsonProperty("vcs_milestone_url")
    private String vcsMilestoneUrl;

    @JsonProperty("progress_status")
    private Byte progressStatus;

    @JsonProperty("workspace_id")
    private Long workspaceId;
}
