package com.threeping.syncday.vcs.query.service;

import com.threeping.syncday.vcs.query.repository.VcsUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VcsUserServiceImpl implements VcsUserService {

    private final VcsUserMapper vcsUserMapper;

    @Autowired
    public VcsUserServiceImpl(VcsUserMapper vcsUserMapper) {
        this.vcsUserMapper = vcsUserMapper;
    }
}
