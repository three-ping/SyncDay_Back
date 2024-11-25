package com.threeping.syncday.proj.query.controller;

import com.threeping.syncday.proj.query.service.ProjService;
import com.threeping.syncday.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projs")
@Slf4j
public class ProjController {

    private final ProjService projService;

    @Autowired
    public ProjController(ProjService projService){
        this.projService = projService;
    }

    @GetMapping("/")
    public ResponseDTO<?> findAllProjs(){
        return ResponseDTO.ok(projService.getAllProjs());
    }

    @GetMapping("/{projId}")
    public ResponseDTO<?> findProjById(@PathVariable("projId") Long projId){
        return ResponseDTO.ok(projService.getProjById(projId));
    }

    /* userId에 해당하는 프로젝트와 워크스페이스 리스트 반환 */
    @GetMapping("users/{userId}")
    public ResponseDTO<?> findProjInfosByUserId(@PathVariable("userId") Long userId){
        return ResponseDTO.ok(projService.getProjInfosByUserId(userId));
    }

}
