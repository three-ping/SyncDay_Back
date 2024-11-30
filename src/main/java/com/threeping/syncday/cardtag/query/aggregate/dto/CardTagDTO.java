package com.threeping.syncday.cardtag.query.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CardTagDTO {
    @JsonProperty("tag_id")
    private Long tagId;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("color")
    private String color;

    @JsonProperty("workspace_id")
    private Long workspaceId;
}
