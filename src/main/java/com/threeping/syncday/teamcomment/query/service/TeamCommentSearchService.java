package com.threeping.syncday.teamcomment.query.service;

import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentSearchResponse;

import java.util.List;

public interface TeamCommentSearchService {
    List<CommentSearchResponse> searchComments(String keyword);
}
