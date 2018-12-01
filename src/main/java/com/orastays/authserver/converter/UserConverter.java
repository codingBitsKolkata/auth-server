/**
 * @author Avirup
 */
package com.orastays.authserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;

@Component
public class UserConverter extends CommonConverter implements BaseConverter<UserEntity, UserModel>{

	private static final long serialVersionUID = 2114839403217497717L;
	private static final Logger logger = LogManager.getLogger(UserConverter.class);

	@Override
	public UserEntity modelToEntity(UserModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserEntity userEntity = new UserEntity();
		userEntity = (UserEntity) Util.transform(modelMapper, m, userEntity);
		userEntity.setStatus(Status.ACTIVE.ordinal());
		userEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userEntity.setCreatedDate(Util.getCurrentDateTime());
		userEntity.setIsEmailVerified("false");
		userEntity.setIsMobileVerified("false");
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userEntity;
	}

	@Override
	public UserModel entityToModel(UserEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserModel userModel = new UserModel();
		userModel = (UserModel) Util.transform(modelMapper, e, userModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userModel;
	}

	@Override
	public List<UserModel> entityListToModelList(List<UserEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserModel> userModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userModels = new ArrayList<>();
			for(UserEntity userEntity:es) {
				userModels.add(entityToModel(userEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userModels;
	}
}