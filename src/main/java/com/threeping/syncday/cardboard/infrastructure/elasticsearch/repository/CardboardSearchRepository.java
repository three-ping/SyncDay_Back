package com.threeping.syncday.cardboard.infrastructure.elasticsearch.repository;

import com.threeping.syncday.cardboard.infrastructure.elasticsearch.document.CardBoardDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardboardSearchRepository extends ElasticsearchRepository<CardBoardDocument, Long> {
    @Query("{"
            + "\"bool\": {"
            + "  \"should\": ["
            + "    {\"wildcard\": {"
            + "      \"cardboardName\": \"*?0*\""
            + "    }},"
            + "    {\"wildcard\": {"
            + "      \"vcsType\": \"*?0*\""
            + "    }}"
            + "  ]"
            + "}}")
    List<CardBoardDocument> searchByKeyword(String keyword);
}
