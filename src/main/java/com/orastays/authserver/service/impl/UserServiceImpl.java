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

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.model.LanguageModel;
import com.orastays.authserver.model.UserModel;
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
	public LanguageModel checkLanguage(String languageId) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("checkLanguage -- START");
		}
		
		LanguageModel languageModel = null;
		
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put(AuthConstant.STATUS, String.valueOf(Status.ACTIVE.ordinal()));
			innerMap1.put(AuthConstant.LANGUAGEID, languageId);
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".LanguageEntity", outerMap1);
	
			languageModel = languageConverter.entityToModel(languageDAO.fetchObjectBySubCiteria(alliasMap));

		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("checkLanguage -- END");
		}
		
		return languageModel;
	}
}