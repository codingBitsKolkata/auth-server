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

import com.orastays.authserver.entity.IdentityEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.IdentityModel;

@Component
public class IdentityConverter extends CommonConverter implements BaseConverter<IdentityEntity, IdentityModel>{

	private static final long serialVersionUID = 571647450186121650L;
	private static final Logger logger = LogManager.getLogger(IdentityConverter.class);

	@Override
	public IdentityEntity modelToEntity(IdentityModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		IdentityEntity identityEntity = new IdentityEntity();
		identityEntity = (IdentityEntity) Util.transform(modelMapper, m, identityEntity);
		identityEntity.setStatus(Status.INACTIVE.ordinal());
		identityEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		identityEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return identityEntity;
	}

	@Override
	public IdentityModel entityToModel(IdentityEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		IdentityModel identityModel = null;
		if(Objects.nonNull(e)) {
			identityModel = new IdentityModel();
			identityModel = (IdentityModel) Util.transform(modelMapper, e, identityModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return identityModel;
	}

	@Override
	public List<IdentityModel> entityListToModelList(List<IdentityEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<IdentityModel> identityModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			identityModels = new ArrayList<>();
			for(IdentityEntity identityEntity:es) {
				identityModels.add(entityToModel(identityEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return identityModels;
	}
}