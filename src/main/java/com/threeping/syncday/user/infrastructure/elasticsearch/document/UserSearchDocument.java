package com.threeping.syncday.user.infrastructure.elasticsearch.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "user_search")
@Getter
@Builder
public class UserSearchDocument {
    @Id
    private Long userId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Keyword)
    private String email;
    @Field(type = FieldType.Keyword)
    private String profileImage;
    @Field(type = FieldType.Keyword)
    private String teamName;
    @Field(type = FieldType.Keyword)
    private String position;
}
