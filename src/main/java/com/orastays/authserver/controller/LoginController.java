/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.UserModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Login", tags = "Login")
public class LoginController extends BaseController {

	private static final Logger logger = LogManager.getLogger(SignUpController.class);
	
	@PostMapping(value = "/login", produces = "application/json")
	@ApiOperation(value = "User Login", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 303, message = "Please Select Country"),
			@ApiResponse(code = 304, message = "Invalid Country"),
			@ApiResponse(code = 306, message = "Invalid Mobile Number"),
			@ApiResponse(code = 318, message = "Mobile Number Not Registered"),
			@ApiResponse(code = 309, message = "Invalid Email ID"),
			@ApiResponse(code = 319, message = "Email Id Not Registered") })
	public ResponseEntity<ResponseModel> validateLogin(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("validateLogin -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Validate Login", request);
		try {
			UserModel userModel2 = loginService.validateLogin(userModel);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("otp.send.success"));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Validate Login -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Validate Login -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Validate Login", request);

		if (logger.isInfoEnabled()) {
			logger.info("validateLogin -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/validate-login-otp", produces = "application/json")
	@ApiOperation(value = "Validate Login OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 312, message = "Please provide UserID"),
			@ApiResponse(code = 313, message = "Invalid UserID"),
			@ApiResponse(code = 314, message = "Please enter OTP"),
			@ApiResponse(code = 315, message = "Invalid OTP"),
			@ApiResponse(code = 316, message = "OTP expires"),
			@ApiResponse(code = 317, message = "Please provide Device ID") })
	public ResponseEntity<ResponseModel> validateLoginOTP(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("validateLoginOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Validate Login OTP", request);
		try {
			UserModel userModel2 = loginService.validateLoginOTP(userModel);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("user.login.success"));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Validate Login OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Validate Login OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Validate Login OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("validateLoginOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}