package com.threeping.syncday.cardtag.query.repository;

import com.threeping.syncday.cardtag.query.aggregate.dto.CardTagDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardTagMapper {
    CardTagDTO selectCardTagById(Long tagId);
    List<CardTagDTO> selectCardTagsByWorkspaceId(Long tagId);
    CardTagDTO selectByTagName(String tagName);
}
