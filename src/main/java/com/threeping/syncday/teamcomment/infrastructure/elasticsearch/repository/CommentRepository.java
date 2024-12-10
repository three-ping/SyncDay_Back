package com.threeping.syncday.teamcomment.infrastructure.elasticsearch.repository;

import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.document.CommentSearchDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends ElasticsearchRepository<CommentSearchDocument, Long> {
    @Query("{" +
            "  \"bool\": {" +
            "    \"should\": [" +
            "      {\"bool\": {" +
            "        \"should\": [" +
            "          {\"term\": {" +
            "            \"content\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 3.0" +
            "            }" +
            "          }}," +
            "          {\"term\": {" +
            "            \"content.ngram\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 2.0" +
            "            }" +
            "          }}" +
            "        ]" +
            "      }}," +
            "      {\"bool\": {" +
            "        \"should\": [" +
            "          {\"term\": {" +
            "            \"authorName\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 1.5" +
            "            }" +
            "          }}," +
            "          {\"term\": {" +
            "            \"authorName.ngram\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 1.0" +
            "            }" +
            "          }}" +
            "        ]" +
            "      }}," +
            "      {\"bool\": {" +
            "        \"should\": [" +
            "          {\"term\": {" +
            "            \"postTitle\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 2.5" +
            "            }" +
            "          }}," +
            "          {\"term\": {" +
            "            \"postTitle.ngram\": {" +
            "              \"value\": \"?0\"," +
            "              \"boost\": 1.5" +
            "            }" +
            "          }}" +
            "        ]" +
            "      }}" +
            "    ]," +
            "    \"minimum_should_match\": 1" +
            "  }" +
            "}")
    List<CommentSearchDocument> searchByKeyword(String keyword);
}
