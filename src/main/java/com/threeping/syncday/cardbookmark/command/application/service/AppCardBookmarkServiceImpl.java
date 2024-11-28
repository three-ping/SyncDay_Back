package com.threeping.syncday.cardbookmark.command.application.service;

import com.threeping.syncday.cardbookmark.command.aggregate.dto.CardBookmarkDTO;
import com.threeping.syncday.cardbookmark.command.aggregate.entity.CardBookmark;
import com.threeping.syncday.cardbookmark.command.domain.repository.CardBookmarkRepository;
import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AppCardBookmarkServiceImpl implements AppCardBookmarkService {

    private final CardBookmarkRepository cardBookmarkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppCardBookmarkServiceImpl(CardBookmarkRepository cardBookmarkRepository,
                                      ModelMapper modelMapper) {
        this.cardBookmarkRepository = cardBookmarkRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardBookmarkDTO addBookmark(Long userId, Long cardId) {
        if(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId)){
            throw new CommonException(ErrorCode.CARD_BOOKMARK_ALREADY_EXISTS);
        }
        try {
            CardBookmark bookmark = new CardBookmark();
            bookmark.setUserId(userId);
            bookmark.setCardId(cardId);
            CardBookmark addedBookmark = cardBookmarkRepository.save(bookmark);
            return modelMapper.map(addedBookmark, CardBookmarkDTO.class);
        } catch(Exception e){
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean removeBookmark(Long userId, Long cardId) {
        if(cardBookmarkRepository.existsByUserIdAndCardId(userId, cardId)){
            throw new CommonException(ErrorCode.CARD_BOOKMARK_NOT_FOUND);
        }
        try{
            return cardBookmarkRepository.deleteByUserIdAndCardId(userId, cardId);
        } catch(Exception e){
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }


}
