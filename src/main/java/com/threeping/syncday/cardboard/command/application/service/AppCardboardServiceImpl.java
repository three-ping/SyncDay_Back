package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.entity.Cardboard;
import com.threeping.syncday.cardboard.command.aggreate.vo.AddCardboardVO;
import com.threeping.syncday.cardboard.domain.repository.CardboardRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppCardboardServiceImpl implements AppCardboardService {

    private final CardboardRepository cardboardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppCardboardServiceImpl(CardboardRepository cardboardRepository,
                                   ModelMapper modelMapper) {
        this.cardboardRepository = cardboardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardboardDTO addCardboard(AddCardboardVO cardboardVO) {
        Cardboard cardboard = modelMapper.map(cardboardVO, Cardboard.class);
        return null;
    }
}
