package com.threeping.syncday.vcs.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.vcs.command.aggregate.dto.VcsInstallationDTO;
import com.threeping.syncday.vcs.command.aggregate.entity.*;
import com.threeping.syncday.vcs.command.aggregate.request.GithubInstallationRequest;
import com.threeping.syncday.vcs.command.aggregate.response.GithubInstallationResponse;
import com.threeping.syncday.vcs.command.domain.repository.UserVcsInstallationRepository;
import com.threeping.syncday.vcs.command.domain.repository.VcsInstallationRepository;
import com.threeping.syncday.vcs.infrastructure.github.GithubAppClient;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHTargetType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AppGithubServiceImpl implements AppGithubService {

    private final GithubAppClient githubAppClient;
    private final UserVcsInstallationRepository userVcsInstallationRepository;
    private final VcsInstallationRepository vcsInstallationRepository;
    private final ModelMapper modelMapper;

    @Override
    public GithubInstallationResponse processInstallationAuth(GithubInstallationRequest req) {

        try {
            switch (req.getSetupAction()){
                case INSTALL -> handleNewInstallation(req);
                default -> {
                    throw new CommonException(ErrorCode.UNKNOWN_SETUP_ACTION);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(ErrorCode.GITHUB_APP_INSTALLATION_FAILURE);
        }


        return null;
    }
    private VcsInstallationDTO handleNewInstallation(GithubInstallationRequest request) throws IOException {

        GHAppInstallation installation = githubAppClient.getGithubInstallation(request.getInstallationId());
        log.info("installation: {}", installation);
        VcsInstallation installationEntity = new VcsInstallation();
        UserVcsInstallation userVcsInstallation = new UserVcsInstallation();
        // 기본 정보 설정
        installationEntity.setInstallationId(String.valueOf(request.getInstallationId()));
        installationEntity.setAccountId(String.valueOf(installation.getAccount().getId()));
        installationEntity.setAccountName(installation.getAccount().getLogin());

        installationEntity.setAccountType(convertAccountType(installation.getTargetType()));

        installationEntity.setAvatarUrl(installation.getAccount().getAvatarUrl());
        installationEntity.setHtmlUrl(installation.getAccount().getHtmlUrl().toString());
        installationEntity.setApiUrl(installation.getAccount().getUrl().toString());
        installationEntity.setStatus(InstallationStatus.ACTIVE);
        installationEntity.setVcsType(VcsType.GITHUB);
        Long savedInstallationId = vcsInstallationRepository.save(installationEntity).getId();
        userVcsInstallation.setUserId(request.getUserId());
        userVcsInstallation.setInstallationId(savedInstallationId);
        userVcsInstallation.setScope(installation.getPermissions().toString());
        userVcsInstallationRepository.save(userVcsInstallation);
        return modelMapper.map(installationEntity, VcsInstallationDTO.class);
    }
    private AccountType convertAccountType(GHTargetType targetType){
        switch (targetType){
            case USER -> {
                return AccountType.USER;
            }
            case ORGANIZATION -> {
                return AccountType.ORGANIZATION;
            }
            default -> {
                return null;
            }

        }
    }
}
