package com.threeping.syncday.cardboard.command.application.service;

import com.threeping.syncday.cardboard.command.aggreate.dto.CardboardDTO;
import com.threeping.syncday.cardboard.command.aggreate.entity.Cardboard;
import com.threeping.syncday.cardboard.command.aggreate.vo.AppCardboardVO;
import com.threeping.syncday.cardboard.command.domain.repository.CardboardRepository;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public CardboardDTO addCardboard(AppCardboardVO cardboardVO) {
        Cardboard cardboard = modelMapper.map(cardboardVO, Cardboard.class);
        log.info("cardboard: {}", cardboard);
        Cardboard savedCardboard = cardboardRepository.save(cardboard);
        return modelMapper.map(savedCardboard, CardboardDTO.class);
    }

    @Override
    @Transactional
    public CardboardDTO modifyCardboard(AppCardboardVO cardboardVO) {
        Cardboard foundCardboard = cardboardRepository.findById(cardboardVO.getCardboardId()).orElse(null);
        if(foundCardboard == null) {
            throw new CommonException(ErrorCode.CARDBOARD_NOT_FOUND);
        }
        Cardboard savedCardboard = cardboardRepository.save(foundCardboard);
        log.info("savedCardboard: {}", savedCardboard);
        return modelMapper.map(savedCardboard, CardboardDTO.class);
    }

    @Override
    @Transactional
    public CardboardDTO deleteCardboard(Long cardboardId) {
        Cardboard foundCardboard = cardboardRepository.findById(cardboardId).orElse(null);
        if(foundCardboard == null) {
            throw new CommonException(ErrorCode.CARDBOARD_NOT_FOUND);
        }
        cardboardRepository.delete(foundCardboard);
        return modelMapper.map(foundCardboard, CardboardDTO.class);
    }
}
