package com.threeping.syncday.projmember.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.modelmapper.internal.bytebuddy.asm.Advice;

@Data
public class UpdateWorkspaceRequest {
    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("proj_id")
    Long projId;
    @JsonProperty("workspace_id")
    Long workspaceId;
    @JsonProperty("workspace_name")
    String workspaceName;

    @JsonProperty("vcs_repo_name")
    String vcsRepoName;
    @JsonProperty("vcs_repo_url")
    String vcsRepoUrl;
}


