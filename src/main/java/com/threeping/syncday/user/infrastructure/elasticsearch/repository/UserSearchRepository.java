package com.threeping.syncday.user.infrastructure.elasticsearch.repository;

import com.threeping.syncday.user.infrastructure.elasticsearch.document.UserSearchDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSearchRepository extends ElasticsearchRepository<UserSearchDocument, Long> {
    @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"teamName\": \"?0\"}}]}}")
    List<UserSearchDocument> searchByKeyword(String keyword);
}
