package com.threeping.syncday.github.command.application.service;


import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.github.command.aggregate.entity.GithubInstallationEntity;
import com.threeping.syncday.github.command.aggregate.enums.InstallationStatus;
import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationDetails;
import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationRequest;
import com.threeping.syncday.github.command.aggregate.payload.GithubInstallationResponse;
import com.threeping.syncday.github.command.domain.GithubInstallationRepository;
import com.threeping.syncday.github.infrastructure.github.GithubAppClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.kohsuke.github.GHAppInstallation;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AppGithubInstallationServiceImpl implements AppGithubInstallationService {

    private final GithubInstallationRepository installationRepository;
    private final GithubAppClient githubClient;
    private final AppGithubRepositoryService appGithubRepositoryService;

    @Override
    public GithubInstallationResponse processInstallationAuth(GithubInstallationRequest request) {
        try {
            switch (request.getSetupAction()) {
                case INSTALL -> {
                    return handleNewInstallation(request);
                }
//               case UPDATE -> {
//                   return handleInstallationUpdate(request);
//               }
//               case SUSPEND -> {
//                   return handleInstallationSuspend(request);
//               }
//               case UNINSTALL -> {
//                   return handleInstallationUnsuspend(request);
//               }
                default -> {
                    throw new CommonException(ErrorCode.UNKNOWN_SETUP_ACTION);
                }
            }
        } catch (Exception e) {
            throw new CommonException(ErrorCode.GITHUB_APP_INSTALLATION_FAILURE);
        }
    }

    private GithubInstallationResponse handleNewInstallation(GithubInstallationRequest request) throws IOException {

        GHAppInstallation installation = githubClient.getGithubInstallation(request.getInstallationId());
        GithubInstallationEntity installationEntity = new GithubInstallationEntity();
        // 기본 정보 설정
        installationEntity.setInstallationId(installation.getId());
        installationEntity.setAccountId(installation.getAccount().getId());
        installationEntity.setAccountName(installation.getAccount().getLogin());
        installationEntity.setAccountType(installation.getTargetType());
        installationEntity.setAvatarUrl(installation.getAccount().getAvatarUrl());
        installationEntity.setHtmlUrl(installation.getAccount().getHtmlUrl().toString());
        installationEntity.setStatus(InstallationStatus.ACTIVE);
        installationEntity.setUserId(request.getUserId());
        GithubInstallationEntity savedEntity = installationRepository.save(installationEntity);
        return convertToResponse(savedEntity);
    }

    private GithubInstallationResponse convertToResponse(GithubInstallationEntity entity) {
        GithubInstallationDetails details = GithubInstallationDetails.builder()
                .id(entity.getId())
                .installationId(entity.getInstallationId())
                .accountName(entity.getAccountName())
                .accountType(entity.getAccountType())
                .avatarUrl(entity.getAvatarUrl())
                .htmlUrl(entity.getHtmlUrl())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

        return GithubInstallationResponse.builder()
                .success(true)
                .message("new installation")
                .installationDetails(details)
                .build();
    }


}