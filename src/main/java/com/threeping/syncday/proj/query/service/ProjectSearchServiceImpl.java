package com.threeping.syncday.proj.query.service;

import com.threeping.syncday.proj.infrastructure.elasticsearch.repository.ProjSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectSearchServiceImpl {

    private final ProjSearchRepository projSearchRepository;

    @Autowired
    public ProjectSearchServiceImpl(ProjSearchRepository projSearchRepository) {
        this.projSearchRepository = projSearchRepository;
    }
}
