/**
 * @author Avirup
 */
package com.orastays.authserver.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.authserver.entity.CountryEntity;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.CountryModel;

@Component
public class CountryConverter extends CommonConverter implements BaseConverter<CountryEntity, CountryModel>{

	private static final long serialVersionUID = 5636379780132029943L;
	private static final Logger logger = LogManager.getLogger(CountryConverter.class);

	
	@Override
	public CountryEntity modelToEntity(CountryModel m) {
		
		return null;
	}

	@Override
	public CountryModel entityToModel(CountryEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		CountryModel countryModel = null;
		if(Objects.nonNull(e)) {
			countryModel = new CountryModel();
			countryModel = (CountryModel) Util.transform(modelMapper, e, countryModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return countryModel;
	}

	@Override
	public List<CountryModel> entityListToModelList(List<CountryEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<CountryModel> countryModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			countryModels = new ArrayList<>();
			for(CountryEntity countryEntity:es) {
				countryModels.add(entityToModel(countryEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return countryModels;
	}
}