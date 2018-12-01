/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.entity.CountryEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserTypeEntity;
import com.orastays.authserver.entity.UserVsTypeEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.SignUpService;

@Service
@Transactional
public class SignUpServiceImpl extends BaseServiceImpl implements SignUpService {

	private static final Logger logger = LogManager.getLogger(SignUpServiceImpl.class);
	
	@Override
	public void signUp(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("signUp -- START");
		}
		
		signUpValidation.validateSignUp(userModel);
		userModel.setOtp(String.valueOf(Util.generateOTP()));
		CountryEntity countryEntity = countryDAO.find(Long.parseLong(userModel.getCountryModel().getCountryId()));
		UserEntity userEntity = userConverter.modelToEntity(userModel);
		userEntity.setCountryEntity(countryEntity);
		Long userId = (Long) userDAO.save(userEntity);
		UserTypeEntity userTypeEntity = userTypeDAO.find(Long.parseLong(String.valueOf(Status.ACTIVE.ordinal())));
		UserVsTypeEntity userVsTypeEntity = new UserVsTypeEntity();
		userVsTypeEntity.setUserEntity(userDAO.find(userId));
		userVsTypeEntity.setUserTypeEntity(userTypeEntity);
		userVsTypeDAO.save(userVsTypeEntity);
		// TODO Call SMS MS
		// TODO Call Email MS
		
		if (logger.isInfoEnabled()) {
			logger.info("signUp -- END");
		}
	}
	
	@Override
	public UserModel fetchUserByMobileNumber(String mobileNumber) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByMobileNumber -- START");
		}
		
		UserModel userModel = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<String, String>();
			innerMap1.put("status", "1");
			innerMap1.put("mobileNumber", mobileNumber);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<String, Map<String, String>>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
	
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
			Map<String, String> innerMap1 = new LinkedHashMap<String, String>();
			innerMap1.put("status", "1");
			innerMap1.put("emailId", emailId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<String, Map<String, String>>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
	
			userModel = userConverter.entityToModel(userDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByEmail -- END");
		}
		
		return userModel;
	}
}