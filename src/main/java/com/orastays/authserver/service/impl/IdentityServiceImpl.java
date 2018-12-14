/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserVsIdentityEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.IdentityModel;
import com.orastays.authserver.model.UserVsIdentityModel;
import com.orastays.authserver.service.IdentityService;
import com.orastays.authserver.service.UserService;

@Service
@Transactional
public class IdentityServiceImpl extends BaseServiceImpl implements IdentityService {

	private static final Logger logger = LogManager.getLogger(IdentityServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<IdentityModel> fetchIdentities(String userToken) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchIdentities -- START");
		}
		
		if(Objects.isNull(userService.checkToken(userToken))) {
			Map<String, Exception> exceptions = new LinkedHashMap<>();
			exceptions.put(messageUtil.getBundle("token.invalid.code"), new Exception(messageUtil.getBundle("token.invalid.message")));
			throw new FormExceptions(exceptions);
		}
		
		List<IdentityModel> identityModels = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".IdentityEntity", outerMap1);
	
			identityModels = identityConverter.entityListToModelList(identityDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchIdentities -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchIdentities -- END");
		}
		
		return identityModels;
	}
	
	@Override
	public void addUserIdentity(UserVsIdentityModel userVsIdentityModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addUserIdentity -- START");
		}
		
		UserEntity userEntity = userValidation.validateUserIdentity(userVsIdentityModel);
		userVsIdentityModel.setUserModel(userConverter.entityToModel(userEntity));
		UserVsIdentityEntity userVsIdentityEntity = userVsIdentityConverter.modelToEntity(userVsIdentityModel);
		userVsIdentityDAO.save(userVsIdentityEntity);
		
		if (logger.isInfoEnabled()) {
			logger.info("addUserIdentity -- END");
		}
	}
}