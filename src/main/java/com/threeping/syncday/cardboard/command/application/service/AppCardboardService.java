package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.vo.AddCardboardVO;

public interface AppCardboardService {
    CardboardDTO addCardboard(AddCardboardVO cardboardVO);
}
