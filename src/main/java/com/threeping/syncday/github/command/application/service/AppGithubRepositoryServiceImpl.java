package com.threeping.syncday.github.command.application.service;


import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.command.domain.GithubRepoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppGithubRepositoryServiceImpl implements AppGithubRepositoryService {

    private final GithubRepoRepository githubRepoRepository;

    @Override
    public Boolean saveInstallationRepositories(Long installationId, GHAppInstallation installation){
        try {


        }catch (Exception e){
            throw new CommonException(ErrorCode.GITHUB_INSTLLATION_REPOSITORY_SAVE_FAILURE);
        }

        return Boolean.FALSE;
    }



}
