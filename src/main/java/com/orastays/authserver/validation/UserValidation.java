package com.orastays.authserver.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;

@Component
@Transactional
public class UserValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(UserValidation.class);
	
	public UserEntity validateFetchUserByID(String userId) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchUserByID -- Start");
		}

		Util.printLog(userId, AuthConstant.INCOMING, "Fetch User Details By UserID", request);
		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		// Validate User Id
		if(StringUtils.isBlank(userId)) {
			exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
		} else {
			userEntity = userDAO.find(Long.parseLong(userId));
			if(Objects.isNull(userEntity)) {
				exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchUserByID -- End");
		}
		
		return userEntity;
	}
	
	public UserEntity validateCheckToken(String userToken) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateCheckToken -- Start");
		}

		Util.printLog(userToken, AuthConstant.INCOMING, "Fetch User Details By UserID", request);
		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		LoginDetailsEntity loginDetailsEntity = null;
				
		// Validate User Id
		if(StringUtils.isBlank(userToken)) {
			exceptions.put(messageUtil.getBundle("user.token.null.code"), new Exception(messageUtil.getBundle("user.token.null.message")));
		} else {
			
			try {
				Map<String, String> innerMap1 = new LinkedHashMap<>();
				innerMap1.put("status", "1");
				innerMap1.put("sessionToken", userToken);
		
				Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
				outerMap1.put("eq", innerMap1);
		
				Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
				alliasMap.put(entitymanagerPackagesToScan+".LoginDetailsEntity", outerMap1);
		
				loginDetailsEntity = loginDetailsDAO.fetchObjectBySubCiteria(alliasMap);
				if(Objects.isNull(loginDetailsEntity)) {
					exceptions.put(messageUtil.getBundle("user.token.invalid.code"), new Exception(messageUtil.getBundle("user.token.invalid.message")));
				} else {
					if(StringUtils.isBlank(loginDetailsEntity.getModifiedDate())) {
						if(Util.getMinuteDiff(loginDetailsEntity.getCreatedDate()) > Integer.parseInt(messageUtil.getBundle("session.timeout"))) {
							exceptions.put(messageUtil.getBundle("session.expires.code"), new Exception(messageUtil.getBundle("session.expires.message")));
						}
					} else {
						if(Util.getMinuteDiff(loginDetailsEntity.getModifiedDate()) > Integer.parseInt(messageUtil.getBundle("session.timeout"))) {
							exceptions.put(messageUtil.getBundle("session.expires.code"), new Exception(messageUtil.getBundle("session.expires.message")));
						}
					}
				}
			} catch (Exception e) {
				exceptions.put(messageUtil.getBundle("user.token.invalid.code"), new Exception(messageUtil.getBundle("user.token.invalid.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			userEntity = loginDetailsEntity.getUserEntity();
			loginDetailsEntity.setModifiedBy(userEntity.getUserId());
			loginDetailsEntity.setModifiedDate(Util.getCurrentDateTime());
			loginDetailsDAO.update(loginDetailsEntity);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateCheckToken -- End");
		}
		
		return userEntity;
	}
}