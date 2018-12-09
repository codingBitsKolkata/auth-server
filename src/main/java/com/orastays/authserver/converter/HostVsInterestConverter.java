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

import com.orastays.authserver.entity.HostVsInterestEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.HostVsInterestModel;

@Component
public class HostVsInterestConverter extends CommonConverter
		implements BaseConverter<HostVsInterestEntity, HostVsInterestModel> {

	private static final long serialVersionUID = 7158006269514503276L;
	private static final Logger logger = LogManager.getLogger(HostVsInterestConverter.class);

	@Override
	public HostVsInterestEntity modelToEntity(HostVsInterestModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		HostVsInterestEntity hostVsInterestEntity = new HostVsInterestEntity();
		hostVsInterestEntity = (HostVsInterestEntity) Util.transform(modelMapper, m, hostVsInterestEntity);
		hostVsInterestEntity.setStatus(Status.INACTIVE.ordinal());
		hostVsInterestEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		hostVsInterestEntity.setCreatedDate(Util.getCurrentDateTime());

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}

		return hostVsInterestEntity;
	}

	@Override
	public HostVsInterestModel entityToModel(HostVsInterestEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		HostVsInterestModel hostVsInterestModel = null;
		if (Objects.nonNull(e)) {
			hostVsInterestModel = new HostVsInterestModel();
			hostVsInterestModel = (HostVsInterestModel) Util.transform(modelMapper, e, hostVsInterestModel);
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return hostVsInterestModel;
	}

	@Override
	public List<HostVsInterestModel> entityListToModelList(List<HostVsInterestEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<HostVsInterestModel> hostVsInterestModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			hostVsInterestModels = new ArrayList<>();
			for (HostVsInterestEntity hostVsInterestEntity : es) {
				hostVsInterestModels.add(entityToModel(hostVsInterestEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return hostVsInterestModels;
	}
}