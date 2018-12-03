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

import com.orastays.authserver.entity.LanguageEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.LanguageModel;

@Component
public class LanguageConverter extends CommonConverter implements BaseConverter<LanguageEntity, LanguageModel>{

	private static final long serialVersionUID = -1921360658332143596L;
	private static final Logger logger = LogManager.getLogger(LanguageConverter.class);

	@Override
	public LanguageEntity modelToEntity(LanguageModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		LanguageEntity languageEntity = new LanguageEntity();
		languageEntity = (LanguageEntity) Util.transform(modelMapper, m, languageEntity);
		languageEntity.setStatus(Status.INACTIVE.ordinal());
		languageEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		languageEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return languageEntity;
	}

	@Override
	public LanguageModel entityToModel(LanguageEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		LanguageModel languageModel = null;
		if(Objects.nonNull(e)) {
			languageModel = new LanguageModel();
			languageModel = (LanguageModel) Util.transform(modelMapper, e, languageModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return languageModel;
	}

	@Override
	public List<LanguageModel> entityListToModelList(List<LanguageEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<LanguageModel> languageModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			languageModels = new ArrayList<>();
			for(LanguageEntity languageEntity:es) {
				languageModels.add(entityToModel(languageEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return languageModels;
	}
}