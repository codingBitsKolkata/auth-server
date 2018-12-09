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

import com.orastays.authserver.entity.HostVsDomainEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.HostVsDomainModel;

@Component
public class HostVsDomainConverter extends CommonConverter
		implements BaseConverter<HostVsDomainEntity, HostVsDomainModel> {

	private static final long serialVersionUID = -9169002869195931046L;
	private static final Logger logger = LogManager.getLogger(HostVsDomainConverter.class);

	@Override
	public HostVsDomainEntity modelToEntity(HostVsDomainModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		HostVsDomainEntity hostVsDomainEntity = new HostVsDomainEntity();
		hostVsDomainEntity = (HostVsDomainEntity) Util.transform(modelMapper, m, hostVsDomainEntity);
		hostVsDomainEntity.setStatus(Status.INACTIVE.ordinal());
		hostVsDomainEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		hostVsDomainEntity.setCreatedDate(Util.getCurrentDateTime());

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}

		return hostVsDomainEntity;
	}

	@Override
	public HostVsDomainModel entityToModel(HostVsDomainEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		HostVsDomainModel hostVsDomainModel = null;
		if (Objects.nonNull(e)) {
			hostVsDomainModel = new HostVsDomainModel();
			hostVsDomainModel = (HostVsDomainModel) Util.transform(modelMapper, e, hostVsDomainModel);
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return hostVsDomainModel;
	}

	@Override
	public List<HostVsDomainModel> entityListToModelList(List<HostVsDomainEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<HostVsDomainModel> hostVsDomainModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			hostVsDomainModels = new ArrayList<>();
			for (HostVsDomainEntity hostVsDomainEntity : es) {
				hostVsDomainModels.add(entityToModel(hostVsDomainEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return hostVsDomainModels;
	}
}