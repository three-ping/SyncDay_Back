package com.threeping.syncday.proj.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.proj.command.aggregate.dto.ProjDTO;
import com.threeping.syncday.proj.command.application.service.AppProjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/proj")
public class AppProjController {
    private final AppProjService appProjService;

    @Autowired
    public AppProjController(AppProjService appProjService){
        this.appProjService = appProjService;
    }

    @PostMapping("/")
    public ResponseDTO<?> addProj(@RequestBody ProjDTO newProj){
        log.info("newProj: {}", newProj);
        return ResponseDTO.ok(appProjService.addProj(newProj));
    }
}
