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

import com.orastays.authserver.entity.UserActivityEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserVsInfoEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserActivityModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.model.UserVsInfoModel;
import com.orastays.authserver.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public UserModel fetchUserByID(String userId) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByID -- START");
		}
		
		UserEntity userEntity = userValidation.validateFetchUserByID(userId);
		UserModel userModel = null;
		if(userEntity.getStatus() == Status.ACTIVE.ordinal())
			userModel = userConverter.entityToModel(userEntity);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByID -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserModel checkToken(String userToken) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("checkToken -- START");
		}
		
		UserEntity userEntity = userValidation.validateCheckToken(userToken);
		UserModel userModel = null;
		if(userEntity.getStatus() == Status.ACTIVE.ordinal())
			userModel = userConverter.entityToModel(userEntity);
		
		if (logger.isInfoEnabled()) {
			logger.info("checkToken -- END");
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
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
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
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchUserByMobileNumber -- "+Util.errorToString(e));
			}
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
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put("emailId", emailId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
	
			userModel = userConverter.entityToModel(userDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchUserByEmail -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByEmail -- END");
		}
		
		return userModel;
	}
	
	@Override
	public UserEntity validateUserByMobileNumber(String mobileNumber, String countryId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateUserByMobileNumber -- START");
		}
		
		UserEntity userEntity = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
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
	
			userEntity = userDAO.fetchObjectBySubCiteria(alliasMap);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in validateUserByMobileNumber -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("validateUserByMobileNumber -- END");
		}
		
		return userEntity;
	}
	
	@Override
	public UserEntity validateUserByEmail(String emailId) {
		
		if (logger.isInfoEnabled()) {
			logger.info("validateUserByEmail -- START");
		}
		
		UserEntity userEntity = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("emailId", emailId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".UserEntity", outerMap1);
	
			userEntity = userDAO.fetchObjectBySubCiteria(alliasMap);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in validateUserByEmail -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("validateUserByEmail -- END");
		}
		
		return userEntity;
	}

	@Override
	public void addUserActivity(UserActivityModel userActivityModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- START");
		}
		
		UserEntity userEntity = userValidation.validateUserActivity(userActivityModel);
		userActivityModel.setUserModel(userConverter.entityToModel(userEntity));
		UserActivityEntity userActivityEntity = userActivityConverter.modelToEntity(userActivityModel);
		userActivityDAO.save(userActivityEntity);
		
		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- END");
		}
	}

	@Override
	public UserModel updateUserInfo(UserVsInfoModel userVsInfoModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- START");
		}
		
		UserVsInfoEntity userVsInfoEntity = userValidation.validateUserInfo(userVsInfoModel);
		userVsInfoDAO.update(userVsInfoEntity);
		UserModel userModel = userConverter.entityToModel(userDAO.find(userVsInfoEntity.getModifiedBy()));
		
		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- END");
		}
		
		return userModel;
	}
}