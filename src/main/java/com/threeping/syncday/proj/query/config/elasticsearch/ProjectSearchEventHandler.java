package com.threeping.syncday.proj.query.config.elasticsearch;

import com.threeping.syncday.proj.command.aggregate.dto.ProjectEventDTO;
import com.threeping.syncday.proj.infrastructure.elasticsearch.document.ProjectSearchDocument;
import com.threeping.syncday.proj.infrastructure.elasticsearch.repository.ProjSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProjectSearchEventHandler {

    private final ProjSearchRepository projSearchRepository;

    @Autowired
    public ProjectSearchEventHandler(ProjSearchRepository projSearchRepository) {
        this.projSearchRepository = projSearchRepository;
    }

    @EventListener
    public void handleProjEvent(ProjectEventDTO dto) {
        ProjectSearchDocument document = ProjectSearchDocument
                .builder()
                .projectId(dto.getProjectId())
                .projectName(dto.getProjectName())
                .vcsType(dto.getVcsType())
                .createdAt(dto.getCreatedAt())
                .build();

        switch (dto.getEventType()) {
            case "CREATE", "UPDATE" -> projSearchRepository.save(document);
            case "DELETE" -> projSearchRepository.delete(document);
            default -> throw new IllegalArgumentException("Unknown event type: " + dto.getEventType());
        }
    }
}