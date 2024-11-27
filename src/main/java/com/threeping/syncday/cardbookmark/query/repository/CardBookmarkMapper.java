package com.threeping.syncday.cardbookmark.query.repository;

import com.threeping.syncday.cardbookmark.query.aggregate.dto.CardBookmarkDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardBookmarkMapper {
    List<CardBookmarkDTO> selectCardBookmarksByUserId(Long userId);

}
