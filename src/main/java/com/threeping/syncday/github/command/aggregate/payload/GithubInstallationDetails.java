package com.threeping.syncday.github.command.aggregate.payload;

import lombok.Value;

import java.util.List;

@Value
public class GithubInstallationDetails {
    Long installationId;
    String accountName;
    String accountType;
    String status;
    String repositorySelection; // all or selected
    List<String> permissions;
    List<String> events;
}
