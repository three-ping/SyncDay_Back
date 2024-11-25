package com.threeping.syncday.workspace.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardVO {
    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("card_title")
    private String cardTitle;

    @JsonProperty("card_content")
    private String cardContent;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("start_time")
    private Timestamp startTime;

    @JsonProperty("end_time")
    private Timestamp endTime;

    @JsonProperty("vcs_object_type")
    private String vcsObjectType;

    @JsonProperty("vcs_object_url")
    private String vcsObjectUrl;

    @JsonProperty("cardboard_id")
    private Long cardboardId;

    // Tag information
    @JsonProperty("tag_id")
    private Long tagId;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("tag_color")
    private String tagColor;

    // Cardboard information
    @JsonProperty("cardboard_title")
    private String cardboardTitle;

    @JsonProperty("workspace_id")
    private Long workspaceId;

    // Creator information
    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("creator_name")
    private String creatorName;

    @JsonProperty("creator_email")
    private String creatorEmail;

    @JsonProperty("creator_profile_url")
    private String creatorProfileUrl;

    @JsonProperty("creator_team_id")
    private Long creatorTeamId;

    // Assignee information
    @JsonProperty("assignee_id")
    private Long assigneeId;

    @JsonProperty("assignee_name")
    private String assigneeName;

    @JsonProperty("assignee_email")
    private String assigneeEmail;

    @JsonProperty("assignee_profile_url")
    private String assigneeProfileUrl;

    @JsonProperty("assignee_team_id")
    private Long assigneeTeamId;

    // Team information
    @JsonProperty("creator_team_name")
    private String creatorTeamName;

    @JsonProperty("assignee_team_name")
    private String assigneeTeamName;
}