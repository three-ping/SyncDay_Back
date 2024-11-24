package com.threeping.syncday.workspace.command.domain.repository;

import com.threeping.syncday.workspace.command.aggregate.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
