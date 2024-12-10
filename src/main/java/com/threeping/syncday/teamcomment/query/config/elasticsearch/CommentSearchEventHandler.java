package com.threeping.syncday.teamcomment.query.config.elasticsearch;

import com.threeping.syncday.proj.command.aggregate.dto.ProjectEventDTO;
import com.threeping.syncday.proj.infrastructure.elasticsearch.document.ProjectSearchDocument;
import com.threeping.syncday.proj.infrastructure.elasticsearch.repository.ProjSearchRepository;
import com.threeping.syncday.teamcomment.command.aggregate.dto.CommentEventDTO;
import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.document.CommentSearchDocument;
import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.repository.CommentSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CommentSearchEventHandler {

    private final CommentSearchRepository commentSearchRepository;

    @Autowired
    public CommentSearchEventHandler(CommentSearchRepository commentSearchRepository) {
        this.commentSearchRepository = commentSearchRepository;
    }

    @EventListener
    public void handleProjEvent(CommentEventDTO dto) {
        CommentSearchDocument document = CommentSearchDocument
                .builder()
                .commentId(dto.getCommentId())
                .content(dto.getContent())
                .authorId(dto.getAuthorId())
                .authorName(dto.getAuthorName())
                .position(dto.getPosition())
                .postId(dto.getPostId())
                .postTitle(dto.getPostTitle())
                .updatedAt(dto.getUpdatedAt())
                .createdAt(dto.getCreatedAt())
                .build();

        switch (dto.getEventType()) {
            case "CREATE", "UPDATE" -> commentSearchRepository.save(document);
            case "DELETE" -> commentSearchRepository.delete(document);
            default -> throw new IllegalArgumentException("Unknown event type: " + dto.getEventType());
        }
    }
}