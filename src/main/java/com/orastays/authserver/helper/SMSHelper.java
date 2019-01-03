package com.orastays.authserver.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.SMSModel;
import com.orastays.authserver.model.UserModel;

@Component
@Configuration
@EnableAsync
public class SMSHelper {

	private static final Logger logger = LogManager.getLogger(SMSHelper.class);
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod="smsServerDown")
	@Async
	public void sendSMS(UserModel userModel, String body) {
		
		if (logger.isInfoEnabled()) {
			logger.info("sendSMS -- START");
		}
		
		SMSModel smsModel = new SMSModel();
		smsModel.setMobileNumber(userModel.getCountryModel().getCountryCode() + userModel.getMobileNumber());
		String message = "Use "+ userModel.getMobileOTP() + " "+ body;
		smsModel.setMessage(message);
		ResponseModel response = this.restTemplate.postForObject(messageUtil.getBundle("sms.server.url"), smsModel, ResponseModel.class);
		
		if (logger.isInfoEnabled()) {
			logger.info("sendSMS -- END");
			logger.info("response -- " + response.getResponseMessage());
		}
	}
	
	public void smsServerDown() {
		 System.err.println("Server Down");
	}
}