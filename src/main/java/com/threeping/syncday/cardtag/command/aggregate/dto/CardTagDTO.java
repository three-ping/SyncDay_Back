package com.threeping.syncday.cardtag.command.aggregate.dto;

import lombok.Data;

@Data
public class CardTagDTO {
    private Long tagId;
    private String tagName;
    private String color;
    private Long WorkspaceId;
}
