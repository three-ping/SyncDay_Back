package com.threeping.syncday.workspace.infrastructure.elasticsearch.repository;

import com.threeping.syncday.workspace.infrastructure.elasticsearch.document.WorkSpaceDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSpaceSearchRepository extends ElasticsearchRepository<WorkSpaceDocument, Long> {
    @Query("{"
            + "\"bool\": {"
            + "  \"should\": ["
            + "    {\"wildcard\": {"
            + "      \"workspaceName\": \"*?0*\""
            + "    }}"
            + "  ]"
            + "}}")
    List<WorkSpaceDocument> searchByKeyword(String keyword);
}
