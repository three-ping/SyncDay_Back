package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.vo.AppCardboardVO;

public interface AppCardboardService {
    CardboardDTO addCardboard(AppCardboardVO cardboardVO);

    CardboardDTO modifyCardboard(AppCardboardVO cardboardVO);
}
