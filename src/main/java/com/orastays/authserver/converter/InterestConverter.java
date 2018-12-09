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

import com.orastays.authserver.entity.InterestEntity;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.InterestModel;

@Component
public class InterestConverter extends CommonConverter implements BaseConverter<InterestEntity, InterestModel> {

	private static final long serialVersionUID = -8274793937901681800L;
	private static final Logger logger = LogManager.getLogger(InterestConverter.class);

	@Override
	public InterestEntity modelToEntity(InterestModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		InterestEntity interestEntity = new InterestEntity();
		interestEntity = (InterestEntity) Util.transform(modelMapper, m, interestEntity);
		interestEntity.setStatus(Status.INACTIVE.ordinal());
		interestEntity.setCreatedBy(Long.parseLong(String.valueOf(Status.ZERO.ordinal())));
		interestEntity.setCreatedDate(Util.getCurrentDateTime());

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}

		return interestEntity;
	}

	@Override
	public InterestModel entityToModel(InterestEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		InterestModel interestModel = null;
		if (Objects.nonNull(e)) {
			interestModel = new InterestModel();
			interestModel = (InterestModel) Util.transform(modelMapper, e, interestModel);
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return interestModel;
	}

	@Override
	public List<InterestModel> entityListToModelList(List<InterestEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<InterestModel> interestModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			interestModels = new ArrayList<>();
			for (InterestEntity interestEntity : es) {
				interestModels.add(entityToModel(interestEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return interestModels;
	}
}