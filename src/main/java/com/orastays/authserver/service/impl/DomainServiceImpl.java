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
import com.orastays.authserver.model.DomainModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.DomainService;
import com.orastays.authserver.service.UserService;

@Service
@Transactional
public class DomainServiceImpl extends BaseServiceImpl implements DomainService {

	private static final Logger logger = LogManager.getLogger(DomainServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<DomainModel> fetchDomains(String userToken) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchDomains -- START");
		}
		
		if(Objects.isNull(userService.checkToken(userToken))) {
			Map<String, Exception> exceptions = new LinkedHashMap<>();
			exceptions.put(messageUtil.getBundle("token.invalid.code"), new Exception(messageUtil.getBundle("token.invalid.message")));
			throw new FormExceptions(exceptions);
		}
		
		List<DomainModel> domainModels = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".DomainEntity", outerMap1);
	
			domainModels = domainConverter.entityListToModelList(domainDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchDomains -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchDomains -- END");
		}
		
		return domainModels;
	}

	@Override
	public void addHostDomain(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addHostDomain -- START");
		}
		
		userValidation.validateHostDomain(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("addHostDomain -- END");
		}
	}
}