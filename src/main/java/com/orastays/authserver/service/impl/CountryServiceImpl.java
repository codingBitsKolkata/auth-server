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

import com.orastays.authserver.helper.Status;
import com.orastays.authserver.model.CountryModel;
import com.orastays.authserver.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl extends BaseServiceImpl implements CountryService {

	private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);
	
	@Override
	public List<CountryModel> fetchCountries() {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchCountries -- START");
		}
		
		List<CountryModel> countryModels = new ArrayList<>();
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
	
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);
	
			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan+".CountryEntity", outerMap1);
	
			countryModels = countryConverter.entityListToModelList(countryDAO.fetchListBySubCiteria(alliasMap));
		} catch (Exception e) {
			
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchCountries -- END");
		}
		
		return countryModels;
	}
}