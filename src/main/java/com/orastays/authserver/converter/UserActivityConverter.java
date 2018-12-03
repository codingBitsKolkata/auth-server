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

import com.orastays.authserver.entity.UserActivityEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserActivityModel;

@Component
public class UserActivityConverter extends CommonConverter implements BaseConverter<UserActivityEntity, UserActivityModel>{

	private static final long serialVersionUID = -2057927007876035261L;
	private static final Logger logger = LogManager.getLogger(UserActivityConverter.class);

	@Override
	public UserActivityEntity modelToEntity(UserActivityModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserActivityEntity userActivityEntity = new UserActivityEntity();
		userActivityEntity = (UserActivityEntity) Util.transform(modelMapper, m, userActivityEntity);
		userActivityEntity.setStatus(Status.INACTIVE.ordinal());
		userActivityEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userActivityEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userActivityEntity;
	}

	@Override
	public UserActivityModel entityToModel(UserActivityEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserActivityModel userActivityModel = null;
		if(Objects.nonNull(e)) {
			userActivityModel = new UserActivityModel();
			userActivityModel = (UserActivityModel) Util.transform(modelMapper, e, userActivityModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userActivityModel;
	}

	@Override
	public List<UserActivityModel> entityListToModelList(List<UserActivityEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserActivityModel> userActivityModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userActivityModels = new ArrayList<>();
			for(UserActivityEntity userActivityEntity:es) {
				userActivityModels.add(entityToModel(userActivityEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userActivityModels;
	}
}