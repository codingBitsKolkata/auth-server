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

import com.orastays.authserver.entity.DomainEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.DomainModel;

@Component
public class DomainConverter extends CommonConverter implements BaseConverter<DomainEntity, DomainModel>{

	private static final long serialVersionUID = 981354967450377434L;
	private static final Logger logger = LogManager.getLogger(DomainConverter.class);

	@Override
	public DomainEntity modelToEntity(DomainModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		DomainEntity domainEntity = new DomainEntity();
		domainEntity = (DomainEntity) Util.transform(modelMapper, m, domainEntity);
		domainEntity.setStatus(Status.INACTIVE.ordinal());
		domainEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		domainEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return domainEntity;
	}

	@Override
	public DomainModel entityToModel(DomainEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		DomainModel domainModel = null;
		if(Objects.nonNull(e)) {
			domainModel = new DomainModel();
			domainModel = (DomainModel) Util.transform(modelMapper, e, domainModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return domainModel;
	}

	@Override
	public List<DomainModel> entityListToModelList(List<DomainEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<DomainModel> domainModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			domainModels = new ArrayList<>();
			for(DomainEntity domainEntity:es) {
				domainModels.add(entityToModel(domainEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return domainModels;
	}
}