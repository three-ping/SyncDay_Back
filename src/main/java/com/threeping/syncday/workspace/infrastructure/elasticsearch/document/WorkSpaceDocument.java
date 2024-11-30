package com.threeping.syncday.workspace.infrastructure.elasticsearch.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "workspace_search")
@Getter
@Builder
public class WorkSpaceDocument {

    @Id
    private Long workspaceId;
    @Field(type = FieldType.Text, analyzer = "nori_mixed")
    private String workspaceName;
    @Field(type = FieldType.Keyword)
    private Long projectId;
    @Field(type = FieldType.Keyword)
    private String projectName;
    @Field(type = FieldType.Text, analyzer = "nori_mixed")
    private String vcsType;
    @Field(type = FieldType.Keyword)
    private String vcsRepoUrl;
    @Field(type = FieldType.Text, analyzer = "nori_mixed")
    private String vcsRepoName;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdAt;
}
