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

import com.orastays.authserver.entity.UserTypeEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserTypeModel;

@Component
public class UserTypeConverter extends CommonConverter implements BaseConverter<UserTypeEntity, UserTypeModel>{

	private static final long serialVersionUID = -3804159787690323782L;
	private static final Logger logger = LogManager.getLogger(UserTypeConverter.class);

	@Override
	public UserTypeEntity modelToEntity(UserTypeModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserTypeEntity userTypeEntity = new UserTypeEntity();
		userTypeEntity = (UserTypeEntity) Util.transform(modelMapper, m, userTypeEntity);
		userTypeEntity.setStatus(Status.INACTIVE.ordinal());
		userTypeEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userTypeEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userTypeEntity;
	}

	@Override
	public UserTypeModel entityToModel(UserTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserTypeModel userTypeModel = null;
		if(Objects.nonNull(e)) {
			userTypeModel = new UserTypeModel();
			userTypeModel = (UserTypeModel) Util.transform(modelMapper, e, userTypeModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userTypeModel;
	}

	@Override
	public List<UserTypeModel> entityListToModelList(List<UserTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserTypeModel> userTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userTypeModels = new ArrayList<>();
			for(UserTypeEntity userTypeEntity:es) {
				userTypeModels.add(entityToModel(userTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userTypeModels;
	}
}