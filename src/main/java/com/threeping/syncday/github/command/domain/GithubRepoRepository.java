package com.threeping.syncday.github.command.domain;

import com.threeping.syncday.github.command.aggregate.entity.GithubRepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepoRepository extends JpaRepository<GithubRepositoryEntity, Long> {
}
