package com.threeping.syncday.teamcomment.query.config;

import com.threeping.syncday.teamcomment.command.aggregate.dto.CommentEventDTO;
import com.threeping.syncday.teamcomment.command.aggregate.entity.TeamComment;
import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentQueryDTO;
import com.threeping.syncday.teamcomment.query.repository.TeamCommentMapper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TeamCommentEntityListener {

    private final ApplicationEventPublisher eventPublisher;
    private final TeamCommentMapper commentMapper;

    @Autowired
    public TeamCommentEntityListener(ApplicationEventPublisher eventPublisher, TeamCommentMapper commentMapper) {
        this.eventPublisher = eventPublisher;
        this.commentMapper = commentMapper;
    }

    @PostPersist // 프로젝트 생성 시
    @PostUpdate // 프로젝트 수정 시
    public void onPostPersistOrUpdate(TeamComment comment) {
        CommentQueryDTO commentInfo = commentMapper.findCommentById(comment.getTeamCommentId());

        eventPublisher.publishEvent(CommentEventDTO.from(commentInfo));
    }

    // 프로젝트 엔티티가 삭제 시
    @PreRemove
    public void onPreRemove(TeamComment comment) {
        CommentQueryDTO commentInfo = commentMapper.findCommentById(comment.getTeamCommentId());

        eventPublisher.publishEvent(CommentEventDTO.createDeleted(commentInfo));
    }
}
