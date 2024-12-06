package com.threeping.syncday.github.command.aggregate.enums;

import lombok.Getter;
import lombok.Value;

@Getter
public enum SetupAction {
    INSTALL,
    UNINSTALL,
    SUSPEND,
    UNSUSPEND,
    UPDATE
}
