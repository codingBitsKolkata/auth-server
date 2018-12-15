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

import com.orastays.authserver.entity.UserVsLanguageEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserVsLanguageModel;

@Component
public class UserVsLanguageConverter extends CommonConverter implements BaseConverter<UserVsLanguageEntity, UserVsLanguageModel>{

	private static final long serialVersionUID = 7255184596367698444L;
	private static final Logger logger = LogManager.getLogger(UserVsLanguageConverter.class);

	@Override
	public UserVsLanguageEntity modelToEntity(UserVsLanguageModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserVsLanguageEntity userVsLanguageEntity = new UserVsLanguageEntity();
		userVsLanguageEntity = (UserVsLanguageEntity) Util.transform(modelMapper, m, userVsLanguageEntity);
		userVsLanguageEntity.setStatus(Status.INACTIVE.ordinal());
		userVsLanguageEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userVsLanguageEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userVsLanguageEntity;
	}

	@Override
	public UserVsLanguageModel entityToModel(UserVsLanguageEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserVsLanguageModel userVsLanguageModel = null;
		if(Objects.nonNull(e)) {
			userVsLanguageModel = new UserVsLanguageModel();
			userVsLanguageModel = (UserVsLanguageModel) Util.transform(modelMapper, e, userVsLanguageModel);
			userVsLanguageModel.setLanguageModel(languageConverter.entityToModel(e.getLanguageEntity()));
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userVsLanguageModel;
	}

	@Override
	public List<UserVsLanguageModel> entityListToModelList(List<UserVsLanguageEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserVsLanguageModel> userVsLanguageModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userVsLanguageModels = new ArrayList<>();
			for(UserVsLanguageEntity userVsLanguageEntity:es) {
				userVsLanguageModels.add(entityToModel(userVsLanguageEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userVsLanguageModels;
	}
}