package com.threeping.syncday.proj.query.controller;

import com.threeping.syncday.proj.query.service.ProjService;
import com.threeping.syncday.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proj")
@Slf4j
public class ProjController {

    private ProjService projService;

    @Autowired
    public ProjController(ProjService projService){
        this.projService = projService;
    }

    @GetMapping("/")
    public ResponseDTO<?> getAllProjs(){
        return ResponseDTO.ok(projService.getAllProjs());
    }
}
