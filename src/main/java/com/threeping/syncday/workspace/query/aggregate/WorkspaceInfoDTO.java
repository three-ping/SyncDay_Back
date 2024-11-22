package com.threeping.syncday.workspace.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WorkspaceInfoDTO {

    @JsonProperty("workspace_id")
    private Long workspaceId;

    @JsonProperty("cardboards")
    private List<CardBoardVO> cardBoards;
}
