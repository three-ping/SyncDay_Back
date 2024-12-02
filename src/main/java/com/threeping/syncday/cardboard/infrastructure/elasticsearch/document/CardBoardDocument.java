package com.threeping.syncday.cardboard.infrastructure.elasticsearch.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "cardboard_search")
@Getter
@Builder
public class CardBoardDocument {
    @Id
    private Long cardboardId;
    @Field(type = FieldType.Text, analyzer = "nori_mixed")
    private String cardboardName;
    @Field(type = FieldType.Keyword)
    private Long workspaceId;
    @Field(type = FieldType.Keyword)
    private String workspaceName;
    @Field(type = FieldType.Text, analyzer = "nori_mixed")
    private String vcsType;
    @Field(type = FieldType.Keyword)
    private String vcsMilestoneUrl;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdAt;
}
