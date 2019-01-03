/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.orastays.authserver.helper.MessageUtil;
import com.orastays.authserver.service.CountryService;
import com.orastays.authserver.service.DomainService;
import com.orastays.authserver.service.IdentityService;
import com.orastays.authserver.service.InterestService;
import com.orastays.authserver.service.LanguageService;
import com.orastays.authserver.service.LoginService;
import com.orastays.authserver.service.PrivacyPolicyService;
import com.orastays.authserver.service.SignUpService;
import com.orastays.authserver.service.UserService;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected LoginService loginService;
	
	@Autowired
	protected SignUpService signUpService;
	
	@Autowired
	protected CountryService countryService;
	
	@Autowired
	protected PrivacyPolicyService privacyPolicyService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected DomainService domainService;
	
	@Autowired
	protected InterestService interestService;
	
	@Autowired
	protected IdentityService identityService;
	
	@Autowired
	protected LanguageService languageService;
	
	@Autowired
	protected MessageUtil messageUtil;
}
