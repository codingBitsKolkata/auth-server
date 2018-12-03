/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.orastays.authserver.helper.MessageUtil;
import com.orastays.authserver.service.CountryService;
import com.orastays.authserver.service.LoginService;
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
	protected UserService userService;
	
	@Autowired
	protected MessageUtil messageUtil;
}
