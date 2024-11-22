package com.threeping.syncday.card.query.repository;

import com.threeping.syncday.card.query.aggregate.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardMapper {


    List<Card> selectAllCards();
}
