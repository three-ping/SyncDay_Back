package com.threeping.syncday.proj.command.aggregate.dto;

import com.threeping.syncday.proj.query.aggregate.dto.ProjectQueryDTO;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectEventDTO {
    private Long projectId;
    private String projectName;
    private String vcsType;
    private LocalDateTime createdAt;
    private String eventType;

    public static ProjectEventDTO from(ProjectQueryDTO dto) {
        return ProjectEventDTO.builder()
                .projectId(dto.getProjectId())
                .projectName(dto.getProjectName())
                .vcsType(dto.getVcsType())
                .createdAt(convertToLocalDateTime(dto.getCreatedAt()))
                .eventType("CREATE")
                .build();
    }

    public static ProjectEventDTO createDeleted(ProjectQueryDTO dto) {
        return ProjectEventDTO.builder()
                .projectId(dto.getProjectId())
                .projectName(dto.getProjectName())
                .vcsType(dto.getVcsType())
                .createdAt(convertToLocalDateTime(dto.getCreatedAt()))
                .eventType("DELETE")
                .build();
    }

    private static LocalDateTime convertToLocalDateTime(Timestamp createdAt) {
        if (createdAt == null) {
            return null;
        }
        return createdAt.toLocalDateTime();
    }
}
