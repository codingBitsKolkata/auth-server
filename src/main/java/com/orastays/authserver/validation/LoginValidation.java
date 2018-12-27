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
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.UserModel;

@Component
@Transactional
public class LoginValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(LoginValidation.class);
	
	public void validateLogin(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateLogin -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		if(Objects.nonNull(userModel)) {
			
			// Validate Mobile Number of the User
			if(!StringUtils.isBlank(userModel.getMobileNumber())) {
				if(Util.checkMobileNumber(userModel.getMobileNumber())) {
					exceptions.put(messageUtil.getBundle("user.mobile.invalid.code"), new Exception(messageUtil.getBundle("user.mobile.invalid.message")));
				} else {
					
					// Validate Country Code
					if(Objects.nonNull(userModel.getCountryModel())) {
						if(StringUtils.isBlank(userModel.getCountryModel().getCountryId())) {
							exceptions.put(messageUtil.getBundle("country.id.null.code"), new Exception(messageUtil.getBundle("country.id.null.message")));
						} else {
							if(Objects.isNull(countryDAO.find(Long.parseLong(userModel.getCountryModel().getCountryId())))) {
								exceptions.put(messageUtil.getBundle("country.id.invalid.code"), new Exception(messageUtil.getBundle("country.id.invalid.message")));
							} else {
								UserModel userModel2 = userService.fetchUserByMobileNumber(userModel.getMobileNumber(), userModel.getCountryModel().getCountryId());
								if(Objects.isNull(userModel2)) {
									exceptions.put(messageUtil.getBundle("user.mobile.not.present.code"), new Exception(messageUtil.getBundle("user.mobile.not.present.message")));
								} else {
									if(!StringUtils.equals(userModel2.getIsMobileVerified(), AuthConstant.TRUE)) {
										exceptions.put(messageUtil.getBundle("mobile.not.verified.code"), new Exception(messageUtil.getBundle("mobile.not.verified.message")));
									} else {
										userModel.setUserId(userModel2.getUserId());
									}
								}
							}
						}
					}
				}
			} else {
				// Validate Email ID of the User
				if(StringUtils.isBlank(userModel.getEmailId())) {
					exceptions.put(messageUtil.getBundle("user.email.null.code"), new Exception(messageUtil.getBundle("user.email.null.message")));
				} else {
					if(!Util.checkEmail(userModel.getEmailId())) {
						exceptions.put(messageUtil.getBundle("user.email.invalid.code"), new Exception(messageUtil.getBundle("user.email.invalid.message")));
					} else {
						UserModel userModel2 = userService.fetchUserByEmail(userModel.getEmailId());
						if(Objects.isNull(userModel2)) {
							exceptions.put(messageUtil.getBundle("user.email.not.present.code"), new Exception(messageUtil.getBundle("user.email.not.present.message")));
						} else {
							if(!StringUtils.equals(userModel2.getIsEmailVerified(), AuthConstant.TRUE)) {
								exceptions.put(messageUtil.getBundle("email.not.verified.code"), new Exception(messageUtil.getBundle("email.not.verified.message")));
							} else {
								userModel.setUserId(userModel2.getUserId());
							}
						}
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateLogin -- End");
		}
		
	}
	
	public UserEntity validateLoginOTP(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateLoginOTP -- Start");
		}

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
				
				// Check with Mobile OTP first
				if(!StringUtils.equals(userModel.getOtp(), userEntity.getMobileOTP())) {
					// Check with Email OTP
					if(!StringUtils.equals(userModel.getOtp(), userEntity.getEmailOTP())) {
						exceptions.put(messageUtil.getBundle("otp.invalid.code"), new Exception(messageUtil.getBundle("otp.invalid.message")));
					} else {
						if(Util.getMinuteDiff(userEntity.getEmailOTPValidity()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
							exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
						}
					}
				} else {
					if(Util.getMinuteDiff(userEntity.getMobileOTPValidity()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
						exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
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
			logger.debug("validateLoginOTP -- End");
		}
		
		return userEntity;
	}
	
	public void validatefetchInactiveUser(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validatefetchInactiveUser -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		if(Objects.nonNull(userModel)) {
			
			// Validate Mobile Number of the User
			if(!StringUtils.isBlank(userModel.getMobileNumber())) {
				if(Util.checkMobileNumber(userModel.getMobileNumber())) {
					exceptions.put(messageUtil.getBundle("user.mobile.invalid.code"), new Exception(messageUtil.getBundle("user.mobile.invalid.message")));
				} else {
					
					// Validate Country Code
					if(Objects.nonNull(userModel.getCountryModel())) {
						if(StringUtils.isBlank(userModel.getCountryModel().getCountryId())) {
							exceptions.put(messageUtil.getBundle("country.id.null.code"), new Exception(messageUtil.getBundle("country.id.null.message")));
						} else {
							if(Objects.isNull(countryDAO.find(Long.parseLong(userModel.getCountryModel().getCountryId())))) {
								exceptions.put(messageUtil.getBundle("country.id.invalid.code"), new Exception(messageUtil.getBundle("country.id.invalid.message")));
							} else {
								UserEntity userEntity = userService.validateUserByMobileNumber(userModel.getMobileNumber(), userModel.getCountryModel().getCountryId());
								if(Objects.nonNull(userEntity) && userEntity.getStatus() != Status.ACTIVE.ordinal()) {
									userModel.setUserId(String.valueOf(userEntity.getUserId()));
								}
							}
						}
					}
				}
			} else {
				// Validate Email ID of the User
				if(StringUtils.isBlank(userModel.getEmailId())) {
					exceptions.put(messageUtil.getBundle("user.email.null.code"), new Exception(messageUtil.getBundle("user.email.null.message")));
				} else {
					if(!Util.checkEmail(userModel.getEmailId())) {
						exceptions.put(messageUtil.getBundle("user.email.invalid.code"), new Exception(messageUtil.getBundle("user.email.invalid.message")));
					} else {
						UserEntity userEntity = userService.validateUserByEmail(userModel.getEmailId());
						if(Objects.nonNull(userEntity) && userEntity.getStatus() != Status.ACTIVE.ordinal()) {
							userModel.setUserId(String.valueOf(userEntity.getUserId()));
						}
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validatefetchInactiveUser -- End");
		}
	}
}