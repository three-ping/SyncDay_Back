package com.threeping.syncday.vcs.command.application.service.service;

import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.application.service.AppProjService;
import com.threeping.syncday.user.security.GithubJwtUtils;
import com.threeping.syncday.vcs.command.aggreagate.entity.VCSInstallation;
import com.threeping.syncday.vcs.command.aggreagate.entity.VcsType;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationCheckRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationResponse;
import com.threeping.syncday.vcs.command.domain.repository.VcsOrgRepository;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class GithubInstallationServiceImpl implements GithubInstallationService {

    private final VcsOrgRepository vcsOrgRepository;
    private final GithubJwtUtils githubJwtUtils;
    private final AppProjService appProjService;

    @Autowired
    public GithubInstallationServiceImpl(VcsOrgRepository vcsOrgRepository
            , GithubJwtUtils githubJwtUtils
    , AppProjService appProjService) {
        this.vcsOrgRepository = vcsOrgRepository;
        this.githubJwtUtils = githubJwtUtils;
        this.appProjService = appProjService;
    }

    @Override
    public Boolean checkGithubInstallation(VcsInstallationCheckRequestVO installationCheckVO) {
        VCSInstallation vcsOrg = vcsOrgRepository.findByVcsOrgIdAndVcsType(installationCheckVO.getVcsOrgId(), installationCheckVO.getVcsType());
        if (vcsOrg != null) {
            log.info("vcsOrg: {}", vcsOrg);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    private GitHub createInstallationGitHub(String installationToken) throws IOException {
        return new GitHubBuilder()
                .withOAuthToken(installationToken)
                .build();
    }

    @Override
    public VcsInstallationResponse handleGithubAppInstallation(VcsInstallationRequestVO requestVO) {
        try {

            String jwtToken = githubJwtUtils.generateJwtToken();
            log.info("jwtToken: {}", jwtToken);
            GitHub gitHub = new GitHubBuilder().withJwtToken(jwtToken).build();
            log.info("gitHub: {}", gitHub.getInstallation());

            try {
                // Verify app authentication first
                GHApp app = gitHub.getApp();
                log.info("Successfully authenticated as GitHub App: {}", app.getSlug());

                // Now try to get the installation
                GHAppInstallation installation = app.getInstallationById(requestVO.getInstallationId());
                log.info("Found installation for org: {}", installation.getAccount().getLogin());

                // Create installation token
                GHAppInstallationToken token = installation.createToken().create();
                VCSInstallation vcsOrg = new VCSInstallation();
                vcsOrg.setUserId(requestVO.getUserId());
                vcsOrg.setVcsOrgLogin(installation.getAccount().getLogin());
                vcsOrg.setOrgUrl(installation.getAccount().getUrl().toString());
                vcsOrg.setVcsType(VcsType.GITHUB);
                vcsOrg.setAvatarUrl(installation.getAccount().getAvatarUrl());
                vcsOrg.setVcsOrgId(installation.getAccount().getId());
                vcsOrg.setInstallationId(installation.getId());
                vcsOrg.setCreatedAt(installation.getCreatedAt());
                vcsOrg.setUpdatedAt(installation.getUpdatedAt());
                vcsOrg.setVcsTargetType(installation.getTargetType());
                log.info("Created token: {}", token);
                log.info("vcsOrg: {}", vcsOrg);
                VCSInstallation savedInstallation = vcsOrgRepository.save(vcsOrg);
                log.info("Saved installation: {}", savedInstallation);
                ProjDTO proj = appProjService.updateVcsInstallation(requestVO.getProjId(), requestVO.getUserId(), savedInstallation.getId());

                return new VcsInstallationResponse(savedInstallation, proj, token.getToken());

            } catch (IOException e) {
                log.error("GitHub API error", e);
                log.error("Response body: {}", e.getMessage());
            }

        } catch (Exception e) {
            log.error("Installation handling failed", e);
            e.printStackTrace();
        }

    return null;
    }

    @Override
    public List<GHProject> getOrganizationProjects(VCSInstallation vcsInstallation) {
        try {
            String jwtToken = githubJwtUtils.generateJwtToken();
            log.info("jwtToken: {}", jwtToken);
            GitHub gitHub = new GitHubBuilder().withJwtToken(jwtToken).build();
            log.info("gitHub: {}", gitHub.getInstallation());
            GHApp app = gitHub.getApp();
            log.info("Successfully authenticated as GitHub App: {}", app.getSlug());

            // Now try to get the installation
            GHAppInstallation installation;
            try {
                // First log the current installation ID you're trying to use
                log.info("Attempting to access installation ID: {}", vcsInstallation.getInstallationId());

                // List all available installations
                PagedIterable<GHAppInstallation> installations = app.listInstallations();

                // Log all available installations
                installations.forEach(inst -> {
                    log.info("Available installation - ID: {}, Account: {}",
                            inst.getId(),
                            inst.getAccount().getLogin());
                });

                // Then try your original code
                installation = app.getInstallationById(vcsInstallation.getInstallationId());
                log.info("Found installation for org: {}", installation.getAccount().getLogin());

                String token = installation.createToken().create().getToken();
                log.info("token: {}", token);

                GitHub installationGitHub = new GitHubBuilder().withJwtToken(token).build();
                log.info("installationGitHub: {}", installationGitHub);
                if (vcsInstallation.getVcsTargetType() == GHTargetType.USER) {
                    GHUser user = installationGitHub.getUser(vcsInstallation.getVcsOrgLogin());
                    return user.listProjects().toList();
                } else {
                    GHOrganization organization = installationGitHub.getOrganization(vcsInstallation.getVcsOrgLogin());
                    return organization.listProjects().toList();

                }
            } catch (IOException e) {
                log.error("Error accessing GitHub installations", e);
                throw new RuntimeException(e);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
