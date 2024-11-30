package com.threeping.syncday.proj.infrastructure.elasticsearch.repository;

import com.threeping.syncday.proj.infrastructure.elasticsearch.document.ProjectSearchDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjSearchRepository extends ElasticsearchRepository<ProjectSearchDocument, Long> {
    @Query("{"
            + "\"bool\": {"
            + "  \"should\": ["
            + "    {\"wildcard\": {"
            + "      \"projectName\": \"*?0*\""
            + "    }},"
            + "    {\"wildcard\": {"
            + "      \"vcsType\": \"*?0*\""
            + "    }}"
            + "  ]"
            + "}}")
    List<ProjectSearchDocument> searchByKeyword(String keyword);
}
