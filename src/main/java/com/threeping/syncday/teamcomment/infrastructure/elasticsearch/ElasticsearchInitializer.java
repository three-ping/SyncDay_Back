package com.threeping.syncday.teamcomment.infrastructure.elasticsearch;

import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.document.CommentSearchDocument;
import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.repository.CommentSearchRepository;
import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentQueryDTO;
import com.threeping.syncday.teamcomment.query.repository.TeamCommentMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component("comment_init")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchInitializer {
    private final TeamCommentMapper commentMapper;
    private final CommentSearchRepository searchRepository;

    @PostConstruct
    public void init() {
        log.info("팀 댓글 ES 동기화 시작!");
        synchronizeAll();
    }

    // 수동 동기화 메서드 추가
    public void synchronizeAll() {
        searchRepository.deleteAll();
//        log.info("ES db와 동기화를 위해 es db 모두 비우기");

        List<CommentQueryDTO> comments = commentMapper.findAllComments();
                    comments.forEach(dto -> {
                    CommentSearchDocument document = CommentSearchDocument.builder()
                            .commentId(dto.getCommentId())
                            .content(dto.getContent())
                            .authorId(dto.getAuthorId())
                            .authorName(dto.getAuthorName())
                            .position(dto.getPosition())
                            .postId(dto.getPostId())
                            .postTitle(dto.getPostTitle())
                            .updatedAt(convertToLocalDateTime(dto.getUpdatedAt()))
                            .createdAt(convertToLocalDateTime(dto.getCreatedAt()))
                            .build();
                    searchRepository.save(document);
                });
    }

    private static LocalDateTime convertToLocalDateTime(Timestamp createdAt) {
        if (createdAt == null) {
            return null;
        }
        return createdAt.toLocalDateTime();
    }
}