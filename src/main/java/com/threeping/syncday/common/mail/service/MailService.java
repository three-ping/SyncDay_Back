package com.threeping.syncday.common.mail.service;

import com.threeping.syncday.common.mail.aggregate.MailType;

import java.util.Map;

public interface MailService {
    void sendMailNotice(String email, String title, Map<String,Object> varMap, MailType mailType);
}
