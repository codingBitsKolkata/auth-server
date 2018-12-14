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

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.InterestModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.InterestService;
import com.orastays.authserver.service.UserService;

@Service
@Transactional
public class InterestServiceImpl extends BaseServiceImpl implements InterestService {

	private static final Logger logger = LogManager.getLogger(InterestServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<InterestModel> fetchInterests(String userToken) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchInterests -- START");
		}
		
		if(Objects.isNull(userService.checkToken(userToken))) {
			Map<String, Exception> exceptions = new LinkedHashMap<>();
			exceptions.put(messageUtil.getBundle("token.invalid.code"), new Exception(messageUtil.getBundle("token.invalid.message")));
			throw new FormExceptions(exceptions);
		}
		
		List<InterestModel> interestModels = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".InterestEntity", outerMap1);
	
			interestModels = interestConverter.entityListToModelList(interestDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchInterests -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchInterests -- END");
		}
		
		return interestModels;
	}

	@Override
	public void addHostInterest(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addHostInterest -- START");
		}
		
		userValidation.validateHostInterest(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("addHostInterest -- END");
		}
	}
}