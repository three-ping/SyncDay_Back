package com.threeping.syncday.team.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamDTO {

    @JsonProperty("team_id")
    Long teamId;

    @JsonProperty("team_name")
    String teamName;
}
