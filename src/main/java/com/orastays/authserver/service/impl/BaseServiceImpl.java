/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.authserver.converter.CountryConverter;
import com.orastays.authserver.converter.DomainConverter;
import com.orastays.authserver.converter.IdentityConverter;
import com.orastays.authserver.converter.InterestConverter;
import com.orastays.authserver.converter.LanguageConverter;
import com.orastays.authserver.converter.LoginDetailsConverter;
import com.orastays.authserver.converter.UserActivityConverter;
import com.orastays.authserver.converter.UserConverter;
import com.orastays.authserver.converter.UserVsIdentityConverter;
import com.orastays.authserver.dao.CountryDAO;
import com.orastays.authserver.dao.DomainDAO;
import com.orastays.authserver.dao.IdentityDAO;
import com.orastays.authserver.dao.InterestDAO;
import com.orastays.authserver.dao.LanguageDAO;
import com.orastays.authserver.dao.LoginDetailsDAO;
import com.orastays.authserver.dao.UserActivityDAO;
import com.orastays.authserver.dao.UserDAO;
import com.orastays.authserver.dao.UserTypeDAO;
import com.orastays.authserver.dao.UserVsIdentityDAO;
import com.orastays.authserver.dao.UserVsInfoDAO;
import com.orastays.authserver.dao.UserVsLanguageDAO;
import com.orastays.authserver.dao.UserVsTypeDAO;
import com.orastays.authserver.helper.MailHelper;
import com.orastays.authserver.helper.MessageUtil;
import com.orastays.authserver.helper.SMSHelper;
import com.orastays.authserver.validation.LoginValidation;
import com.orastays.authserver.validation.SignUpValidation;
import com.orastays.authserver.validation.UserValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected SignUpValidation signUpValidation;
	
	@Autowired
	protected LoginValidation loginValidation;
	
	@Autowired
	protected UserDAO userDAO;
	
	@Autowired
	protected UserConverter userConverter;
	
	@Autowired
	protected UserValidation userValidation;
	
	@Autowired
	protected UserTypeDAO userTypeDAO;
	
	@Autowired
	protected UserActivityConverter userActivityConverter;
	
	@Autowired
	protected UserActivityDAO userActivityDAO;
	
	@Autowired
	protected UserVsTypeDAO userVsTypeDAO; 
	
	@Autowired
	protected UserVsInfoDAO userVsInfoDAO;
	
	@Autowired
	protected UserVsLanguageDAO userVsLanguageDAO;
	
	@Autowired
	protected CountryDAO countryDAO;
	
	@Autowired
	protected CountryConverter countryConverter;
	
	@Autowired
	protected LoginDetailsConverter loginDetailsConverter;
	
	@Autowired
	protected LoginDetailsDAO loginDetailsDAO; 
	
	@Autowired
	protected SMSHelper smsHelper;
	
	@Autowired
	protected MailHelper mailHelper;
	
	@Autowired
	protected LanguageConverter languageConverter;
	
	@Autowired
	protected LanguageDAO languageDAO; 
	
	@Autowired
	protected InterestDAO interestDAO;
	
	@Autowired
	protected InterestConverter interestConverter;
	
	@Autowired
	protected IdentityDAO identityDAO;
	
	@Autowired
	protected IdentityConverter identityConverter;
	
	@Autowired
	protected UserVsIdentityDAO userVsIdentityDAO;
	
	@Autowired
	protected UserVsIdentityConverter userVsIdentityConverter;
	
	@Autowired
	protected DomainDAO domainDAO;
	
	@Autowired
	protected DomainConverter domainConverter;
	
	@Autowired
	protected MessageUtil messageUtil;
}
