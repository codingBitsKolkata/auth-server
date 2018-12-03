package com.orastays.authserver.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;

@Component
@Transactional
public class SignUpValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(SignUpValidation.class);
	
	public void validateSignUp(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateSignUp -- Start");
		}

		Util.printLog(userModel, AuthConstant.INCOMING, "Sign Up", request);
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		if(Objects.nonNull(userModel)) {
			
			// Validate Name of the user
			if(StringUtils.isBlank(userModel.getName())) {
				exceptions.put(messageUtil.getBundle("user.name.null.code"), new Exception(messageUtil.getBundle("user.name.null.message")));
			} else {
				if(!Util.checkAlphabet(userModel.getName())) {
					exceptions.put(messageUtil.getBundle("user.name.invalid.code"), new Exception(messageUtil.getBundle("user.name.invalid.message")));
				}
			}
			
			// Validate Country Code
			if(Objects.nonNull(userModel.getCountryModel())) {
				if(StringUtils.isBlank(userModel.getCountryModel().getCountryId())) {
					exceptions.put(messageUtil.getBundle("country.id.null.code"), new Exception(messageUtil.getBundle("country.id.null.message")));
				} else {
					if(Objects.isNull(countryDAO.find(Long.parseLong(userModel.getCountryModel().getCountryId())))) {
						exceptions.put(messageUtil.getBundle("country.id.invalid.code"), new Exception(messageUtil.getBundle("country.id.invalid.message")));
					}
				}
			}
			
			// Validate Mobile Number of the User
			if(StringUtils.isBlank(userModel.getMobileNumber())) {
				exceptions.put(messageUtil.getBundle("user.mobile.null.code"), new Exception(messageUtil.getBundle("user.mobile.null.message")));
			} else {
				if(Util.checkMobileNumber(userModel.getMobileNumber())) {
					exceptions.put(messageUtil.getBundle("user.mobile.invalid.code"), new Exception(messageUtil.getBundle("user.mobile.invalid.message")));
				} else {
					if(Objects.nonNull(signUpService.fetchUserByMobileNumber(userModel.getMobileNumber(), userModel.getCountryModel().getCountryId()))) {
						exceptions.put(messageUtil.getBundle("user.mobile.present.code"), new Exception(messageUtil.getBundle("user.mobile.present.message")));
					}
					
				}
			}
			
			// Validate Email ID of the User
			if(StringUtils.isBlank(userModel.getEmailId())) {
				exceptions.put(messageUtil.getBundle("user.email.null.code"), new Exception(messageUtil.getBundle("user.email.null.message")));
			} else {
				if(Util.checkEmail(userModel.getEmailId())) {
					exceptions.put(messageUtil.getBundle("user.email.invalid.code"), new Exception(messageUtil.getBundle("user.email.invalid.message")));
				} else {
					if(Objects.nonNull(signUpService.fetchUserByEmail(userModel.getEmailId()))) {
						exceptions.put(messageUtil.getBundle("user.email.present.code"), new Exception(messageUtil.getBundle("user.email.present.message")));
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateSignUp -- End");
		}
	}
	
	public UserEntity validateSendOTP(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateSendOTP -- Start");
		}

		Util.printLog(userModel, AuthConstant.INCOMING, "Validate OTP", request);
		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		if(Objects.nonNull(userModel)) {
			
			// Validate User Id
			if(StringUtils.isBlank(userModel.getUserId())) {
				exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
			} else {
				userEntity = userDAO.find(Long.parseLong(userModel.getUserId()));
				if(Objects.isNull(userEntity)) {
					exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
				}
			}
			
			if (exceptions.size() > 0)
				throw new FormExceptions(exceptions);
			
			// Validate OTP
			if(StringUtils.isBlank(userModel.getOtp())) {
				exceptions.put(messageUtil.getBundle("otp.null.code"), new Exception(messageUtil.getBundle("otp.null.message")));
			} else {
				if(!StringUtils.equals(userModel.getOtp(), userEntity.getOtp())) {
					exceptions.put(messageUtil.getBundle("otp.invalid.code"), new Exception(messageUtil.getBundle("otp.invalid.message")));
				} else {
					if(StringUtils.isBlank(userEntity.getModifiedDate())) {
						if(Util.getMinuteDiff(userEntity.getCreatedDate()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
							exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
						}
					} else {
						if(Util.getMinuteDiff(userEntity.getModifiedDate()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
							exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
						}
					}
				}
			}
			
			// Validate Device ID
			if(StringUtils.isBlank(userModel.getDeviceId())) {
				exceptions.put(messageUtil.getBundle("device.id.null.code"), new Exception(messageUtil.getBundle("device.id.null.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			userModel.setIp(request.getRemoteAddr());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateSendOTP -- End");
		}
		
		return userEntity;
	}
	
	public UserEntity validateReSendOTP(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateReSendOTP -- Start");
		}

		Util.printLog(userModel, AuthConstant.INCOMING, "Validate OTP", request);
		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		if(Objects.nonNull(userModel)) {
			
			// Validate User Id
			if(StringUtils.isBlank(userModel.getUserId())) {
				exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
			} else {
				userEntity = userDAO.find(Long.parseLong(userModel.getUserId()));
				if(Objects.isNull(userEntity)) {
					exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateReSendOTP -- End");
		}
		
		return userEntity;
	}
}