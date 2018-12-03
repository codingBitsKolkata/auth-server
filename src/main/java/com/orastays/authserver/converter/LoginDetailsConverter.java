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

import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.LoginDetailsModel;

@Component
public class LoginDetailsConverter extends CommonConverter implements BaseConverter<LoginDetailsEntity, LoginDetailsModel>{

	private static final long serialVersionUID = 2114839403217497717L;
	private static final Logger logger = LogManager.getLogger(LoginDetailsConverter.class);

	@Override
	public LoginDetailsEntity modelToEntity(LoginDetailsModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		LoginDetailsEntity loginDetailsEntity = new LoginDetailsEntity();
		loginDetailsEntity = (LoginDetailsEntity) Util.transform(modelMapper, m, loginDetailsEntity);
		loginDetailsEntity.setStatus(Status.ACTIVE.ordinal());
		loginDetailsEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		loginDetailsEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return loginDetailsEntity;
	}

	@Override
	public LoginDetailsModel entityToModel(LoginDetailsEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		LoginDetailsModel loginDetailsModel = null;
		if(Objects.nonNull(e)) {
			loginDetailsModel = new LoginDetailsModel();
			loginDetailsModel = (LoginDetailsModel) Util.transform(modelMapper, e, loginDetailsModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return loginDetailsModel;
	}

	@Override
	public List<LoginDetailsModel> entityListToModelList(List<LoginDetailsEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<LoginDetailsModel> loginDetailsModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			loginDetailsModels = new ArrayList<>();
			for(LoginDetailsEntity loginDetailsEntity:es) {
				loginDetailsModels.add(entityToModel(loginDetailsEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return loginDetailsModels;
	}
}