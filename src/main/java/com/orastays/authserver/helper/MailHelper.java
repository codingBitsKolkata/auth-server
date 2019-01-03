package com.orastays.authserver.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.orastays.authserver.model.MailModel;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.UserModel;

@Component
@Configuration
@EnableAsync
public class MailHelper {

	private static final Logger logger = LogManager.getLogger(MailHelper.class);
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Async
	public void sendMail(UserModel userModel, String msgType) {
		
		if (logger.isInfoEnabled()) {
			logger.info("sendMail -- START");
		}
		
		MailModel mailModel = new MailModel();
		mailModel.setEmailId(userModel.getEmailId());
		String message = "Use "+ userModel.getEmailOTP() + " "+ messageUtil.getBundle("otp.sms.message");
		mailModel.setMessageBody(message);
		mailModel.setSubject(msgType);
		ResponseModel response = this.restTemplate.postForObject(messageUtil.getBundle("mail.server.url"), mailModel, ResponseModel.class);
		
		if (logger.isInfoEnabled()) {
			logger.info("sendMail -- END");
			logger.info("response -- " + response.getResponseMessage());
		}
	}
}