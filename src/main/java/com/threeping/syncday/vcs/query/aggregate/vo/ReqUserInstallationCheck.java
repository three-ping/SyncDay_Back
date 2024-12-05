package com.threeping.syncday.vcs.query.aggregate.vo;

import com.threeping.syncday.vcs.common.enums.VcsType;
import lombok.Value;

@Value
public class ReqUserInstallationCheck {
    Long userId;
    VcsType vcsType;
}
