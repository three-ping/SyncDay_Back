package com.threeping.syncday.github.command.aggregate.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

@Getter
public enum SetupAction {
    @JsonProperty("install")
    INSTALL,

    @JsonProperty("update")
    UPDATE,

    @JsonProperty("suspend")
    SUSPEND,

    @JsonProperty("unsuspend")
    UNSUSPEND,

    @JsonProperty("uninstall")
    UNINSTALL
}
