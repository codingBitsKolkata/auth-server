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

import com.orastays.authserver.entity.UserVsIdentityEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserVsIdentityModel;

@Component
public class UserVsIdentityConverter extends CommonConverter implements BaseConverter<UserVsIdentityEntity, UserVsIdentityModel>{

	private static final long serialVersionUID = 6167024606992152563L;
	private static final Logger logger = LogManager.getLogger(UserVsIdentityConverter.class);

	@Override
	public UserVsIdentityEntity modelToEntity(UserVsIdentityModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		UserVsIdentityEntity userVsIdentityEntity = new UserVsIdentityEntity();
		userVsIdentityEntity = (UserVsIdentityEntity) Util.transform(modelMapper, m, userVsIdentityEntity);
		userVsIdentityEntity.setStatus(Status.ACTIVE.ordinal());
		userVsIdentityEntity.setCreatedBy(Long.parseLong(m.getUserModel().getUserId()));
		userVsIdentityEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return userVsIdentityEntity;
	}

	@Override
	public UserVsIdentityModel entityToModel(UserVsIdentityEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		UserVsIdentityModel userVsIdentityModel = null;
		if(Objects.nonNull(e)) {
			userVsIdentityModel = new UserVsIdentityModel();
			userVsIdentityModel = (UserVsIdentityModel) Util.transform(modelMapper, e, userVsIdentityModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return userVsIdentityModel;
	}

	@Override
	public List<UserVsIdentityModel> entityListToModelList(List<UserVsIdentityEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<UserVsIdentityModel> userVsIdentityModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			userVsIdentityModels = new ArrayList<>();
			for(UserVsIdentityEntity userVsIdentityEntity:es) {
				userVsIdentityModels.add(entityToModel(userVsIdentityEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return userVsIdentityModels;
	}
}