package com.threeping.syncday.user.command.application.service;

import com.threeping.syncday.common.exception.CommonException;
import com.threeping.syncday.common.exception.ErrorCode;
import com.threeping.syncday.user.aggregate.oauth.entity.VcsOrg;
import com.threeping.syncday.user.aggregate.oauth.entity.VcsOrgStatus;
import com.threeping.syncday.user.aggregate.oauth.entity.VcsType;
import com.threeping.syncday.user.command.domain.repository.VcsOrgRepository;
import com.threeping.syncday.user.security.GithubJwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// src/main/java/com/syncday/github/service/GithubAppInstallationService.java
@Service
@Slf4j
@RequiredArgsConstructor
public class GithubAppInstallationService {
    private final GitHubBuilder gitHubBuilder;
    private final GithubJwtUtils githubJwtUtils;
    private final VcsOrgRepository vcsOrgRepository;

    public Map<String, Object> validateInstallation(Long installationId) {
        try {
            log.info("Starting installation validation for ID: {}", installationId);

            // Get GitHub instance using JWT
            String jwt = githubJwtUtils.createJwt();
            log.debug("Created JWT token for app authentication");

            GitHub gitHubApp = gitHubBuilder.withJwtToken(jwt).build();
            log.debug("Created GitHub instance with JWT");

            GHAppInstallation installation = gitHubApp.getApp().getInstallationById(installationId);
            log.debug("Retrieved installation: {}", installation.getId());

            // Get organization information
            GHUser account = installation.getAccount();
            String orgName = account.getLogin();
            log.debug("Retrieved organization name: {}", orgName);

            // Get installation token
            GHAppInstallationToken token = installation.createToken().create();
            log.debug("Created installation token");

            // Create new GitHub instance with installation token
            GitHub gitHubWithToken = gitHubBuilder.withOAuthToken(token.getToken()).build();
            log.debug("Created GitHub instance with installation token");

            GHOrganization org = gitHubWithToken.getOrganization(orgName);
            log.debug("Retrieved organization details for: {}", orgName);

            // Save VCS org information
            saveVcsOrg(org, installationId, token);
            log.info("Successfully saved VCS organization information");

            Map<String, Object> response = new HashMap<>();
            response.put("id", org.getId());
            response.put("login", org.getLogin());
            response.put("name", org.getName());
            response.put("avatarUrl", org.getAvatarUrl());
            response.put("htmlUrl", org.getHtmlUrl().toString());
//            response.put("description", org.getDescription());
            response.put("installationId", installationId);

            log.info("Successfully validated installation for ID: {}", installationId);
            return response;

        } catch (IOException e) {
            log.error("Failed to validate installation for ID: {}. Error: {}",
                    installationId, e.getMessage(), e);

            // Get more details about the error
            if (e instanceof GHFileNotFoundException) {
                log.error("Installation or organization not found. This might mean the installation was removed.");
            } else if (e instanceof HttpException) {
                HttpException he = (HttpException) e;
                log.error("HTTP Error occurred. Status: {}, Response: {}",
                        he.getResponseCode(), he.getMessage());
            }

            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveVcsOrg(GHOrganization org, Long installationId, GHAppInstallationToken token) {
        try {
            VcsOrg vcsOrg = vcsOrgRepository.findByInstallationId(installationId);

            if (vcsOrg == null) {
                vcsOrg = new VcsOrg();
            }
            vcsOrg.setVcsType(VcsType.GITHUB);
            vcsOrg.setOrgName(org.getLogin());
            vcsOrg.setOrgUrl(org.getHtmlUrl().toString());
            vcsOrg.setAvatarUrl(org.getAvatarUrl());
            vcsOrg.setVcsOrgId(org.getId());
            vcsOrg.setInstallationId(installationId);
//            vcsOrg.setDescription(org.getDescription());
            vcsOrg.setStatus(VcsOrgStatus.ACTIVE);

            // Get installer information
            GitHub gitHubWithToken = gitHubBuilder.withOAuthToken(token.getToken()).build();
            GHUser installer = gitHubWithToken.getMyself();
            vcsOrg.setUserId(installer.getId());

            vcsOrgRepository.save(vcsOrg);
            log.debug("Saved VCS organization. OrgName: {}, InstallationId: {}", org.getLogin(), installationId);

        } catch (IOException e) {
            log.error("Failed to save VCS organization. OrgId: {}, InstallationId: {}",
                    org.getId(), installationId, e);
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public GHAppInstallationToken getInstallationToken(Long installationId) {
        return null;
    }
}