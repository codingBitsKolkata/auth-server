package com.orastays.authserver.validation;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orastays.authserver.converter.HostVsDomainConverter;
import com.orastays.authserver.converter.HostVsInterestConverter;
import com.orastays.authserver.dao.CountryDAO;
import com.orastays.authserver.dao.HostVsDomainDAO;
import com.orastays.authserver.dao.HostVsInterestDAO;
import com.orastays.authserver.dao.IdentityDAO;
import com.orastays.authserver.dao.LoginDetailsDAO;
import com.orastays.authserver.dao.UserDAO;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.MessageUtil;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.service.FileCopyService;
import com.orastays.authserver.service.SignUpService;
import com.orastays.authserver.service.UserService;

@Component
public class AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(AuthorizeUserValidation.class);
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected MessageUtil messageUtil;
	
	@Autowired
	protected SignUpService signUpService;
	
	@Autowired
	protected CountryDAO countryDAO;
	
	@Autowired
	protected LoginDetailsDAO loginDetailsDAO;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected UserDAO userDAO;
	
	@Autowired
	protected IdentityDAO identityDAO;
	
	@Autowired
	protected HostVsDomainDAO hostVsDomainDAO;
	
	@Autowired
	protected HostVsInterestDAO hostVsInterestDAO;
	
	@Autowired
	protected HostVsDomainConverter hostVsDomainConverter;
	
	@Autowired
	protected HostVsInterestConverter hostVsInterestConverter;
	
	@Autowired
	protected FileCopyService fileCopyService;
	
	@Autowired
	protected HttpServletRequest request;
	
	public UserModel getUserDetails(String authorization, Principal user) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel userModel = new UserModel();
		String jwtBody = authorization.split(" ")[1];
        String[] splitString = jwtBody.split("\\.");
        String base64EncodedBody = splitString[1];
        Base64 base64Url = new Base64(true);


        String body = new String(base64Url.decode(base64EncodedBody));
        Map<String, Object> retMap = new Gson().fromJson(
                  body, new TypeToken<HashMap<String, Object>>() {}.getType()
              );
        
        userModel.setUserId(String.valueOf(retMap.get("userId")));
        userModel.setEmailId(String.valueOf(retMap.get("emailId")));
        /*List<String> roles = (List<String>) retMap.get("authorities");
        userModel.setRole(roles.get(0));*/
		if (logger.isInfoEnabled()) {
			logger.info("userModel ==>> "+userModel);
		}
		if(Util.isEmpty(userModel.getUserId())) {
			exceptions.put(messageUtil.getBundle("user.invalid.code"), new Exception(messageUtil.getBundle("user.invalid.message")));
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("getUserDetails -- END");
		}
		
		return userModel;
	}
}
