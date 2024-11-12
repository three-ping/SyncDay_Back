package com.threeping.syncday.user.command.application.service;


import com.threeping.syncday.user.command.domain.vo.OAuth2LoginVO;

import java.util.Map;

public interface OAuth2Service {

    OAuth2LoginVO processGithubUser(String code);


}
