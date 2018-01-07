package com.freestyle.service.admin.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.freestyle.service.admin.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {
	
	private Logger logger =LoggerFactory.getLogger(getClass());
    
	@Autowired
    private JavaMailSender javaMailSender;
    
	@Autowired
    private SimpleMailMessage simpleMailMessage;
    
  /**
     * @方法名: sendMail 
     * @参数名：@param subject 邮件主题
     * @参数名：@param content 邮件主题内容
     * @参数名：@param to        收件人Email地址
     * @描述语:  发送邮件
     */
	@Override
    public void send(String subject, String content, String to) {
        
        try {
        	simpleMailMessage.setSubject(subject);
        	simpleMailMessage.setSentDate(new Date());
        	simpleMailMessage.setText(content);
        	simpleMailMessage.setTo(to);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
        	logger.error("发送邮件失败",e);
        }
    }
}