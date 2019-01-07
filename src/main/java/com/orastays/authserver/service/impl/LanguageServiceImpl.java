/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.LanguageModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.LanguageService;

@Service
@Transactional
public class LanguageServiceImpl extends BaseServiceImpl implements LanguageService {

	private static final Logger logger = LogManager.getLogger(LanguageServiceImpl.class);
	
	@Override
	public List<LanguageModel> fetchLanguages() {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchLanguages -- START");
		}
		
		List<LanguageModel> languageModels = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".LanguageEntity", outerMap1);
	
			languageModels = languageConverter.entityListToModelList(languageDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchLanguages -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchLanguages -- END");
		}
		
		return languageModels;
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
			if (logger.isInfoEnabled()) {
				logger.info("Exception in checkLanguage -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("checkLanguage -- END");
		}
		
		return languageModel;
	}
	
	@Override
	public void addHostLanguage(UserModel userModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("addHostLanguage -- START");
		}
		
		userValidation.validateHostLanguage(userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("addHostLanguage -- END");
		}
	}
}