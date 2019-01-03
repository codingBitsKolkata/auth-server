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

import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.PrivacyPolicyModel;
import com.orastays.authserver.service.PrivacyPolicyService;

@Service
@Transactional
public class PrivacyPolicyServiceImpl extends BaseServiceImpl implements PrivacyPolicyService {

	private static final Logger logger = LogManager.getLogger(PrivacyPolicyServiceImpl.class);
	
	@Override
	public PrivacyPolicyModel fetchPrivacyPolicy() {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPrivacyPolicy -- START");
		}
		
		PrivacyPolicyModel privacyPolicyModel = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".PrivacyPolicyEntity", outerMap1);
	
			privacyPolicyModel = privacyPolicyConverter.entityToModel(privacyPolicyDAO.fetchObjectBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchPrivacyPolicy -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchPrivacyPolicy -- END");
		}
		
		return privacyPolicyModel;
	}
}