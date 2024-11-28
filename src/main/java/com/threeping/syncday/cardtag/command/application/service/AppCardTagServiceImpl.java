package com.threeping.syncday.cardtag.command.application.service;

import com.threeping.syncday.cardtag.command.aggregate.dto.CardTagDTO;
import com.threeping.syncday.cardtag.command.aggregate.entity.CardTag;
import com.threeping.syncday.cardtag.command.domain.repository.CardTagRepository;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppCardTagServiceImpl implements AppCardTagService {

    private final CardTagRepository cardTagRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppCardTagServiceImpl(CardTagRepository cardTagRepository, ModelMapper modelMapper) {
        this.cardTagRepository = cardTagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardTagDTO addCardTag(CardTagDTO cardTagDTO) {
        try {
            CardTag cardTag = modelMapper.map(cardTagDTO, CardTag.class);
            CardTag addedCardTag = cardTagRepository.save(cardTag);
            return modelMapper.map(addedCardTag, CardTagDTO.class);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CardTagDTO modifyCardTag(CardTagDTO cardTagDTO) {
        CardTag foundCardTag = cardTagRepository.findById(cardTagDTO.getTagId()).orElse(null);
        if (foundCardTag == null) {
            throw new CommonException(ErrorCode.CARD_TAG_NOT_FOUND);
        }
        foundCardTag.setTagName(cardTagDTO.getTagName());
        foundCardTag.setColor(cardTagDTO.getColor());
        CardTag updatedCardTag = modelMapper.map(foundCardTag, CardTag.class);
        return modelMapper.map(updatedCardTag, CardTagDTO.class);
    }

    @Override
    public Boolean removeCardTag(Long cardTagId) {
        CardTag foundCardTag = cardTagRepository.findById(cardTagId).orElse(null);
        if (foundCardTag == null) {
            throw new CommonException(ErrorCode.CARD_TAG_NOT_FOUND);
        }

        cardTagRepository.delete(foundCardTag);
        return Boolean.TRUE;
    }
}
