/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.authserver.converter.CountryConverter;
import com.orastays.authserver.converter.LoginDetailsConverter;
import com.orastays.authserver.converter.UserConverter;
import com.orastays.authserver.dao.CountryDAO;
import com.orastays.authserver.dao.LoginDetailsDAO;
import com.orastays.authserver.dao.UserDAO;
import com.orastays.authserver.dao.UserTypeDAO;
import com.orastays.authserver.dao.UserVsTypeDAO;
import com.orastays.authserver.helper.MailHelper;
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
	protected UserVsTypeDAO userVsTypeDAO; 
	
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
}
