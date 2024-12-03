package com.threeping.syncday.vcs.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.user.aggregate.oauth.vo.GithubAppInstallationRequestVO;
import com.threeping.syncday.vcs.command.aggreagate.vo.VcsInstallationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vcs")
public class AppVcsController {




    @PostMapping("/install")
    public ResponseDTO<?> handleAppInstallation(@RequestBody VcsInstallationRequestVO requestVO){
        return null;
    }

    @GetMapping("/install/check")
    public ResponseDTO<?> checkAppInstallation(){
        return null;
    }
}
