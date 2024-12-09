package com.threeping.syncday.cardboard.command.aggreate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class IssueToCardVO {
    private String title;
    private String body;

    @JsonProperty("vcsObjectUrl")
    private String vcsObjectUrl;

    @JsonProperty("vcsObjectType")
    private String vcsObjectType;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("state")
    private String state;

    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("assignee_avatar")
    private String assigneeAvatar;

}
