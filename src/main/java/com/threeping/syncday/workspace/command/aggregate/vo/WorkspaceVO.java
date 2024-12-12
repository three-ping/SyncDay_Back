package com.threeping.syncday.workspace.command.aggregate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.threeping.syncday.workspace.command.aggregate.entity.VcsType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceVO {
    /* Todo: 유효성 검사를 위해 유저아이디를 추가하여 VO로 생성 */

    @JsonProperty("workspace_id")
     Long workspaceId;

    @JsonProperty("workspace_name")
     String workspaceName;

    @JsonProperty("vcs_type")
     VcsType vcsType;

    @JsonProperty("vcs_repo_name")
     String vcsRepoName;

    @JsonProperty("vcs_repo_url")
     String vcsRepoUrl;

    @JsonProperty("proj_id")
    Long projId;
}
