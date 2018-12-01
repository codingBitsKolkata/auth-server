/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.authserver.converter.UserConverter;
import com.orastays.authserver.dao.CountryDAO;
import com.orastays.authserver.dao.UserDAO;
import com.orastays.authserver.dao.UserTypeDAO;
import com.orastays.authserver.dao.UserVsTypeDAO;
import com.orastays.authserver.validation.SignUpValidation;

public abstract class BaseServiceImpl {

	@Autowired
	protected SignUpValidation signUpValidation;
	
	@Autowired
	protected UserDAO userDAO;
	
	@Autowired
	protected UserConverter userConverter;
	
	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected UserTypeDAO userTypeDAO;
	
	@Autowired
	protected UserVsTypeDAO userVsTypeDAO; 
	
	@Autowired
	protected CountryDAO countryDAO;
}
