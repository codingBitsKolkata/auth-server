/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);
	
	@Override
	public UserModel validateLogin(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("validateLogin -- START");
		}
		
		loginValidation.validateLogin(userModel);
		UserEntity userEntity = userDAO.find(Long.parseLong(userModel.getUserId()));
		userEntity.setEmailOTP(String.valueOf(Util.generateOTP()));
		userEntity.setMobileOTP(String.valueOf(Util.generateOTP()));
		userEntity.setEmailOTPValidity(Util.getCurrentDateTime());
		userEntity.setMobileOTPValidity(Util.getCurrentDateTime());
		userDAO.update(userEntity);
		userModel = userConverter.entityToModel(userEntity);
		smsHelper.sendSMS(userModel);
		mailHelper.sendMail(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateLogin -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserModel validateLoginOTP(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("validateLoginOTP -- START");
		}
		
		UserEntity userEntity = loginValidation.validateLoginOTP(userModel);
		String sessionToken = UUID.randomUUID().toString();
		LoginDetailsEntity loginDetailsEntity = new LoginDetailsEntity();
		loginDetailsEntity.setUserEntity(userEntity);
		loginDetailsEntity.setDeviceId(userModel.getDeviceId());
		loginDetailsEntity.setIp(userModel.getIp());
		loginDetailsEntity.setSessionToken(sessionToken);
		loginDetailsEntity.setStatus(Status.ACTIVE.ordinal());
		loginDetailsEntity.setCreatedBy(userEntity.getUserId());
		loginDetailsEntity.setCreatedDate(Util.getCurrentDateTime());
		loginDetailsDAO.save(loginDetailsEntity);
		userModel = userConverter.entityToModel(userEntity);
		userModel.setUserToken(sessionToken);
		
		if (logger.isInfoEnabled()) {
			logger.info("validateLoginOTP -- END");
		}
		
		return userModel;
	}

	@Override
	public UserModel fetchInactiveUser(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchInactiveUser -- START");
		}
		
		loginValidation.validatefetchInactiveUser(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchInactiveUser -- END");
		}
		
		return userModel;
	}
}