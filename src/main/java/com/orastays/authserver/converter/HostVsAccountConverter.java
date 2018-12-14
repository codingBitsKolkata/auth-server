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

import com.orastays.authserver.entity.HostVsAccountEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.HostVsAccountModel;

@Component
public class HostVsAccountConverter extends CommonConverter implements BaseConverter<HostVsAccountEntity, HostVsAccountModel>{

	private static final long serialVersionUID = 9085582036182090828L;
	private static final Logger logger = LogManager.getLogger(HostVsAccountConverter.class);

	@Override
	public HostVsAccountEntity modelToEntity(HostVsAccountModel m) {
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}
		
		HostVsAccountEntity hostVsAccountEntity = new HostVsAccountEntity();
		hostVsAccountEntity = (HostVsAccountEntity) Util.transform(modelMapper, m, hostVsAccountEntity);
		hostVsAccountEntity.setStatus(Status.INACTIVE.ordinal());
		hostVsAccountEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		hostVsAccountEntity.setCreatedDate(Util.getCurrentDateTime());
		
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		
		return hostVsAccountEntity;
	}

	@Override
	public HostVsAccountModel entityToModel(HostVsAccountEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		HostVsAccountModel hostVsAccountModel = null;
		if(Objects.nonNull(e)) {
			hostVsAccountModel = new HostVsAccountModel();
			hostVsAccountModel = (HostVsAccountModel) Util.transform(modelMapper, e, hostVsAccountModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return hostVsAccountModel;
	}

	@Override
	public List<HostVsAccountModel> entityListToModelList(List<HostVsAccountEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<HostVsAccountModel> hostVsAccountModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			hostVsAccountModels = new ArrayList<>();
			for(HostVsAccountEntity hostVsAccountEntity:es) {
				hostVsAccountModels.add(entityToModel(hostVsAccountEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return hostVsAccountModels;
	}
}