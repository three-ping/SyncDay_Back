package com.threeping.syncday.user.infrastructure.elasticsearch.repository;

import com.threeping.syncday.user.infrastructure.elasticsearch.document.UserSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSearchRepository extends ElasticsearchRepository<UserSearchDocument, Long> {
    List<UserSearchDocument> findByNameContaining(String name);
}
