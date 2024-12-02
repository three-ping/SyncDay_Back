package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.vcs.command.application.service.AppVcsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vcs/org")
public class AppVcsController {
    private final AppVcsService appVcsService;

    @Autowired
    public AppVcsController(AppVcsService appVcsService) {
        this.appVcsService = appVcsService;
    }


}
