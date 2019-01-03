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

import com.orastays.authserver.entity.PrivacyPolicyEntity;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.PrivacyPolicyModel;

@Component
public class PrivacyPolicyConverter extends CommonConverter implements BaseConverter<PrivacyPolicyEntity, PrivacyPolicyModel>{

	private static final long serialVersionUID = -2963439546807644481L;
	private static final Logger logger = LogManager.getLogger(PrivacyPolicyConverter.class);

	
	@Override
	public PrivacyPolicyEntity modelToEntity(PrivacyPolicyModel m) {
		
		return null;
	}

	@Override
	public PrivacyPolicyModel entityToModel(PrivacyPolicyEntity e) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		
		PrivacyPolicyModel privacyPolicyModel = null;
		if(Objects.nonNull(e)) {
			privacyPolicyModel = new PrivacyPolicyModel();
			privacyPolicyModel = (PrivacyPolicyModel) Util.transform(modelMapper, e, privacyPolicyModel);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		
		return privacyPolicyModel;
	}

	@Override
	public List<PrivacyPolicyModel> entityListToModelList(List<PrivacyPolicyEntity> es) {
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		
		List<PrivacyPolicyModel> privacyPolicyModels = null;
		if(!CollectionUtils.isEmpty(es)) {
			privacyPolicyModels = new ArrayList<>();
			for(PrivacyPolicyEntity privacyPolicyEntity:es) {
				privacyPolicyModels.add(entityToModel(privacyPolicyEntity));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		
		return privacyPolicyModels;
	}
}