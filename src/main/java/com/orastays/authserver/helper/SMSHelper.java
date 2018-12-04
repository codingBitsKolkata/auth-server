package com.orastays.authserver.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.SMSModel;
import com.orastays.authserver.model.UserModel;

@Component
public class SMSHelper {

	private static final Logger logger = LogManager.getLogger(SMSHelper.class);
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="smsServerDown")
	public void sendSMS(UserModel userModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("sendSMS -- START");
		}
		
		SMSModel smsModel = new SMSModel();
		smsModel.setMobileNumber(userModel.getMobileNumber());
		String message = "Use "+ userModel.getOtp() + " "+ messageUtil.getBundle("otp.sms.message");
		smsModel.setMessage(message);
		// TODO Rest Template to call SMS Server
		ResponseModel response = this.restTemplate.postForObject("http://SMS-SERVER/api/send-sms", smsModel, ResponseModel.class);
		if (logger.isInfoEnabled()) {
			logger.info("sendSMS -- END");
			logger.info("response -- " + response.getResponseMessage());
		}
	}
	
	public void smsServerDown() {
		 System.err.println("server down");
	}
	
	
}