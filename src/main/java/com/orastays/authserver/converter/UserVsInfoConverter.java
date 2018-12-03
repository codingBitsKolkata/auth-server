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

import com.orastays.authserver.entity.UserVsInfoEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserVsInfoModel;

@Component
public class UserVsInfoConverter extends CommonConverter implements BaseConverter<UserVsInfoEntity, UserVsInfoModel>{

	private static final long serialVersionUID = 4988332664492325963L;
	private static final Logger logger = LogManager.getLogger(UserVsInfoConverter.class);

	@Override
	public UserVsInfoEntity modelToEntity(UserVsInfoModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserVsInfoEntity userVsInfoEntity = new UserVsInfoEntity();
		userVsInfoEntity = (UserVsInfoEntity) Util.transform(modelMapper, m, userVsInfoEntity);
		userVsInfoEntity.setStatus(Status.INACTIVE.ordinal());
		userVsInfoEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		userVsInfoEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userVsInfoEntity;
	}

	@Override
	public UserVsInfoModel entityToModel(UserVsInfoEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserVsInfoModel userVsInfoModel = null;
		if(Objects.nonNull(e)) {
			userVsInfoModel = new UserVsInfoModel();
			userVsInfoModel = (UserVsInfoModel) Util.transform(modelMapper, e, userVsInfoModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userVsInfoModel;
	}

	@Override
	public List<UserVsInfoModel> entityListToModelList(List<UserVsInfoEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserVsInfoModel> userVsInfoModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userVsInfoModels = new ArrayList<>();
			for(UserVsInfoEntity userVsInfoEntity:es) {
				userVsInfoModels.add(entityToModel(userVsInfoEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userVsInfoModels;
	}
}