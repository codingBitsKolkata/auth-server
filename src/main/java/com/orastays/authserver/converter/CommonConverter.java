package com.orastays.authserver.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.orastays.authserver.helper.MessageUtil;

public class CommonConverter {

	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected MessageUtil messageUtil;
	
	
	@Autowired
	protected CountryConverter countryConverter;
	
	@Autowired
	protected IdentityConverter identityConverter;
	
	@Autowired
	protected LanguageConverter languageConverter;
	
	@Autowired
	protected LoginDetailsConverter loginDetailsConverter;
	
	@Autowired
	protected UserConverter userConverter;
	
	@Autowired
	protected UserActivityConverter userActivityConverter;
	
	@Autowired
	protected UserTypeConverter userTypeConverter;
	
	@Autowired
	protected UserVsIdentityConverter userVsIdentityConverter;
	
	@Autowired
	protected UserVsInfoConverter userVsInfoConverter;
	
	@Autowired
	protected UserVsLanguageConverter userVsLanguageConverter;
	
	@Autowired
	protected UserVsTypeConverter userVsTypeConverter;
}
