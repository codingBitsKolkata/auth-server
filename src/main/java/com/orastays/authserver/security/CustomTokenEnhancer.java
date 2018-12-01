package com.orastays.authserver.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	private static final Logger logger = LogManager.getLogger(CustomTokenEnhancer.class);

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		if (logger.isInfoEnabled()) {
			logger.info("enhance -- START");
		}
		
		/*UserModel userModel = userService.fetchUserDetails(authentication.getName());
		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("userId", userModel.getUserId());
		additionalInfo.put("name", userModel.getName());
		additionalInfo.put("emailId", userModel.getEmailId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);*/
		
		if (logger.isInfoEnabled()) {
			logger.info("enhance -- END");
		}
		
		return accessToken;
	}

}
