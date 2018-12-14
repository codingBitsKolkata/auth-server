/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.entity.CountryEntity;
import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserTypeEntity;
import com.orastays.authserver.entity.UserVsInfoEntity;
import com.orastays.authserver.entity.UserVsLanguageEntity;
import com.orastays.authserver.entity.UserVsTypeEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Language;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.UserType;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.SignUpService;

@Service
@Transactional
public class SignUpServiceImpl extends BaseServiceImpl implements SignUpService {

	private static final Logger logger = LogManager.getLogger(SignUpServiceImpl.class);
	
	@Override
	public UserModel signUp(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("signUp -- START");
		}
		
		signUpValidation.validateSignUp(userModel);
		userModel.setOtp(String.valueOf(Util.generateOTP()));
		CountryEntity countryEntity = countryDAO.find(Long.parseLong(userModel.getCountryModel().getCountryId()));
		UserEntity userEntity = userConverter.modelToEntity(userModel);
		userEntity.setCountryEntity(countryEntity);
		Long userId = (Long) userDAO.save(userEntity);
		UserTypeEntity userTypeEntity = userTypeDAO.find(Long.parseLong(String.valueOf(UserType.USER.ordinal())));
		
		UserVsTypeEntity userVsTypeEntity = new UserVsTypeEntity();
		UserEntity userEntity2 = userDAO.find(userId);
		userVsTypeEntity.setUserEntity(userEntity2);
		userVsTypeEntity.setUserTypeEntity(userTypeEntity);
		userVsTypeEntity.setStatus(Status.INACTIVE.ordinal());
		userVsTypeEntity.setCreatedBy(userId);
		userVsTypeEntity.setCreatedDate(Util.getCurrentDateTime());
		userVsTypeDAO.save(userVsTypeEntity);
		
		UserVsInfoEntity userVsInfoEntity = new UserVsInfoEntity();
		userVsInfoEntity.setUserEntity(userEntity2);
		userVsInfoEntity.setName(userModel.getName());
		userVsInfoEntity.setStatus(Status.INACTIVE.ordinal());
		userVsInfoEntity.setCreatedBy(userId);
		userVsInfoEntity.setCreatedDate(Util.getCurrentDateTime());
		userVsInfoDAO.save(userVsInfoEntity);
		
		UserVsLanguageEntity userVsLanguageEntity = new UserVsLanguageEntity();
		userVsLanguageEntity.setLanguageEntity(languageDAO.find(Long.parseLong(String.valueOf(Language.ENGLISH.ordinal()))));
		userVsLanguageEntity.setUserEntity(userEntity2);
		userVsLanguageEntity.setStatus(Status.INACTIVE.ordinal());
		userVsLanguageEntity.setCreatedBy(userId);
		userVsLanguageEntity.setCreatedDate(Util.getCurrentDateTime());
		userVsLanguageDAO.save(userVsLanguageEntity);
		
		userModel = userConverter.entityToModel(userEntity2);
		smsHelper.sendSMS(userModel);
		mailHelper.sendMail(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("signUp -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserModel validateOTP(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("validateOTP -- START");
		}
		
		UserEntity userEntity = signUpValidation.validateSendOTP(userModel);
		userEntity.setStatus(Status.ACTIVE.ordinal());
		userDAO.update(userEntity);
		UserVsTypeEntity userVsTypeEntity = userEntity.getUserVsTypeEntities().get(0);
		userVsTypeEntity.setStatus(Status.ACTIVE.ordinal());
		userVsTypeDAO.update(userVsTypeEntity);
		
		UserVsInfoEntity userVsInfoEntity = userEntity.getUserVsInfoEntity();
		userVsInfoEntity.setStatus(Status.ACTIVE.ordinal());
		userVsInfoDAO.update(userVsInfoEntity);
		
		UserVsLanguageEntity userVsLanguageEntity = userEntity.getUserVsLanguageEntities().get(0);
		userVsLanguageEntity.setStatus(Status.ACTIVE.ordinal());
		userVsLanguageDAO.update(userVsLanguageEntity);
		
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
			logger.info("validateOTP -- END");
		}
		
		return userModel;
	}

	@Override
	public UserModel resendOTP(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("resendOTP -- START");
		}
		
		UserEntity userEntity = signUpValidation.validateReSendOTP(userModel);
		userEntity.setOtp(String.valueOf(Util.generateOTP()));
		userEntity.setModifiedBy(userEntity.getUserId());
		userEntity.setModifiedDate(Util.getCurrentDateTime());
		userDAO.update(userEntity);
		userModel = userConverter.entityToModel(userEntity);
		smsHelper.sendSMS(userModel);
		mailHelper.sendMail(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("resendOTP -- END");
		}
		
		return userModel;
	}
}