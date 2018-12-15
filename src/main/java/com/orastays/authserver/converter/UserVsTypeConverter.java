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

import com.orastays.authserver.entity.UserVsTypeEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserVsTypeModel;

@Component
public class UserVsTypeConverter extends CommonConverter implements BaseConverter<UserVsTypeEntity, UserVsTypeModel>{

	private static final long serialVersionUID = 5961388585245950415L;
	private static final Logger logger = LogManager.getLogger(UserVsTypeConverter.class);

	@Override
	public UserVsTypeEntity modelToEntity(UserVsTypeModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserVsTypeEntity userVsTypeEntity = new UserVsTypeEntity();
		userVsTypeEntity = (UserVsTypeEntity) Util.transform(modelMapper, m, userVsTypeEntity);
		userVsTypeEntity.setStatus(Status.INACTIVE.ordinal());
		userVsTypeEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userVsTypeEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return null;
	}

	@Override
	public UserVsTypeModel entityToModel(UserVsTypeEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserVsTypeModel userVsTypeModel = null;
		if(Objects.nonNull(e)) {
			userVsTypeModel = new UserVsTypeModel();
			userVsTypeModel = (UserVsTypeModel) Util.transform(modelMapper, e, userVsTypeModel);
			userVsTypeModel.setUserTypeModel(userTypeConverter.entityToModel(e.getUserTypeEntity()));
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userVsTypeModel;
	}

	@Override
	public List<UserVsTypeModel> entityListToModelList(List<UserVsTypeEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserVsTypeModel> userVsTypeModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userVsTypeModels = new ArrayList<>();
			for(UserVsTypeEntity userVsTypeEntity:es) {
				userVsTypeModels.add(entityToModel(userVsTypeEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userVsTypeModels;
	}
}