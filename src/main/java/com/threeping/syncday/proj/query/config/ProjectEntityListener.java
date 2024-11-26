package com.threeping.syncday.proj.query.config;

import com.threeping.syncday.proj.command.aggregate.dto.ProjectEventDTO;
import com.threeping.syncday.proj.command.aggregate.entity.Proj;
import com.threeping.syncday.proj.query.aggregate.dto.ProjectQueryDTO;
import com.threeping.syncday.proj.query.repository.ProjMapper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProjectEntityListener {

    private final ApplicationEventPublisher eventPublisher;
    private final ProjMapper projMapper;

    @Autowired
    public ProjectEntityListener(ApplicationEventPublisher eventPublisher, ProjMapper projMapper) {
        this.eventPublisher = eventPublisher;
        this.projMapper = projMapper;
    }

    @PostPersist // 프로젝트 생성 시
    @PostUpdate // 프로젝트 수정 시
    public void onPostPersistOrUpdate(Proj proj) {
        ProjectQueryDTO projInfo = projMapper.findProjById(proj.getProjId());

        eventPublisher.publishEvent(ProjectEventDTO.from(projInfo));
    }

    // 프로젝트 엔티티가 삭제 시
    @PreRemove
    public void onPreRemove(Proj proj) {
        ProjectQueryDTO projInfo = projMapper.findProjById(proj.getProjId());

        eventPublisher.publishEvent(ProjectEventDTO.createDeleted(projInfo));
    }
}
