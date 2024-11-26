package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.query.aggregate.dto.ProjectSearchResponse;

import java.util.List;

public interface ProjectSearchService {
    List<ProjectSearchResponse> searchProject(String keyword);
}
