package com.threeping.syncday.githuborgmember.command.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import lombok.Value;

import java.io.Serializable;

@Value

public class GithubOrgMemberPK implements Serializable {

    @Column(name="user_id")
    String userId;

    @Column(name="github_org_id")
    String githubOrgId;

}
