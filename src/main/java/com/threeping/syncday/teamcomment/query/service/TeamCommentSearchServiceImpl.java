package com.threeping.syncday.teamcomment.query.service;

import com.threeping.syncday.teamcomment.command.domain.repository.TeamCommentRepository;
import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.document.CommentSearchDocument;
import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.repository.CommentSearchRepository;
import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeamCommentSearchServiceImpl implements TeamCommentSearchService {

    private final CommentSearchRepository commentSearchRepository;

    @Autowired
    public TeamCommentSearchServiceImpl(CommentSearchRepository commentSearchRepository) {
        this.commentSearchRepository = commentSearchRepository;
    }

    @Override
    public List<CommentSearchResponse> searchComments(String keyword) {
        log.info("팀댓글 검색요청으로 들어온 키워드: {}", keyword);

        List<CommentSearchResponse> responseList = commentSearchRepository
                .searchByKeyword(keyword).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return responseList;
    }

    private CommentSearchResponse convertToResponse(CommentSearchDocument doc) {
        return CommentSearchResponse
                .builder()
                .commentId(doc.getCommentId())
                .content(doc.getContent())
                .authorId(doc.getAuthorId())
                .authorName(doc.getAuthorName())
                .position(doc.getPosition())
                .postId(doc.getPostId())
                .postTitle(doc.getPostTitle())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
