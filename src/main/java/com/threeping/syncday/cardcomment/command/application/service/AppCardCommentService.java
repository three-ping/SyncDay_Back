package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestAddCardCommentVO;

public interface AppCardCommentService {
    CardCommentDTO addCardComment(RequestAddCardCommentVO cardComment);
}
