package com.threeping.syncday.common.mail.service;

import com.threeping.syncday.common.mail.aggregate.MailType;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    MailServiceImpl(JavaMailSender javaMailSender,
                    SpringTemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void sendMailNotice(String email, String title, Map<String,Object> varMap, MailType mailType){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 수신자 이메일
            mimeMessageHelper.setSubject(title); // 메일제목
            mimeMessageHelper.setText(setContext(varMap, mailType), true); // 첫번째 인자는 내용, 두번째 인자는 html으로 보내는지 여부
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }


    public String setContext(Map<String,Object> varMap,MailType mailType) {
        Context context = new Context();
        context.setVariables(varMap);
        return templateEngine.process(mailType.toString(), context);
    }
}
