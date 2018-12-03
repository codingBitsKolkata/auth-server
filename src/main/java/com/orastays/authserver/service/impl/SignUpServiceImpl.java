/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.entity.CountryEntity;
import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserTypeEntity;
import com.orastays.authserver.entity.UserVsTypeEntity;
import com.orastays.authserver.exceptions.FormExceptions;
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
		userVsTypeEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userVsTypeEntity.setCreatedDate(Util.getCurrentDateTime());
		userVsTypeDAO.save(userVsTypeEntity);
		userModel = userConverter.entityToModel(userEntity2);
		smsHelper.sendSMS(userModel);
		mailHelper.sendMail(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("signUp -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserModel fetchUserByMobileNumber(String mobileNumber, String countryId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByMobileNumber -- START");
		}
		
		UserModel userModel = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", "1");
			innerMap1.put("mobileNumber", mobileNumber);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
			
			Map<String, String> innerMap2 = new LinkedHashMap<>();
			innerMap2.put("countryId", String.valueOf(countryId));
			 
			Map<String, Map<String, String>> outerMap2 = new LinkedHashMap<>();
			outerMap2.put("eq", innerMap2);
			 
			alliasMap.put("countryEntity", outerMap2);
	
			userModel = userConverter.entityToModel(userDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			
		}
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByMobileNumber -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserModel fetchUserByEmail(String emailId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByEmail -- START");
		}
		
		UserModel userModel = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", "1");
			innerMap1.put("emailId", emailId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
	
			userModel = userConverter.entityToModel(userDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByEmail -- END");
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