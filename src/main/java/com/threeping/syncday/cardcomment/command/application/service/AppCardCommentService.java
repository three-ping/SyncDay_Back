package com.threeping.syncday.cardcomment.command.application.service;

import com.threeping.syncday.card.command.aggregate.vo.RequestDeleteCardVO;
import com.threeping.syncday.cardcomment.command.aggregate.dto.CardCommentDTO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestCreateCardCommentVO;
import com.threeping.syncday.cardcomment.command.aggregate.vo.RequestUpdateCardCommentVO;

public interface AppCardCommentService {
    CardCommentDTO addCardComment(RequestCreateCardCommentVO cardComment);

    CardCommentDTO modifyCardComment(RequestUpdateCardCommentVO cardComment);

    Boolean removeCardComment(RequestDeleteCardVO cardComment);
}
