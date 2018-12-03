package com.orastays.authserver.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.authserver.model.MailModel;
import com.orastays.authserver.model.UserModel;

@Component
public class MailHelper {

	private static final Logger logger = LogManager.getLogger(MailHelper.class);
	
	@Autowired
	protected MessageUtil messageUtil;
	
	public void sendMail(UserModel userModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("sendMail -- START");
		}
		
		MailModel mailModel = new MailModel();
		mailModel.setEmailId(userModel.getEmailId());
		String message = "Use "+ userModel.getOtp() + " "+ messageUtil.getBundle("otp.sms.message");
		mailModel.setMessageBody(message);
		mailModel.setSubject(messageUtil.getBundle("otp.mail.subject"));
		// TODO Rest Template to call MAIL Server
		
		if (logger.isInfoEnabled()) {
			logger.info("sendMail -- END");
		}
	}
}