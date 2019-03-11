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
//@RequestMapping("/api")
@Api(value = "Sign Up", tags = "Sign Up")
public class SignUpController extends BaseController {

	private static final Logger logger = LogManager.getLogger(SignUpController.class);
	
	@PostMapping(value = "/sign-up", produces = "application/json")
	@ApiOperation(value = "User Sign Up", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1000, message = "Please Enter Name"),
			@ApiResponse(code = 1001, message = "Invalid Name"),
			@ApiResponse(code = 1002, message = "Please Select Country"),
			@ApiResponse(code = 1003, message = "Invalid Country"),
			@ApiResponse(code = 1004, message = "Please Enter Mobile Number"),
			@ApiResponse(code = 1005, message = "Invalid Mobile Number"),
			@ApiResponse(code = 1006, message = "Mobile Number Already Registered"),
			@ApiResponse(code = 1007, message = "Please Enter Email ID"),
			@ApiResponse(code = 1008, message = "Invalid Email ID"),
			@ApiResponse(code = 1009, message = "Email ID Already Registered"),
			@ApiResponse(code = 1010, message = "User is Inactive"),
			@ApiResponse(code = 1037, message = "Please Check Privacy Policy"),
			@ApiResponse(code = 1038, message = "Invalid Privacy Policy Format") })
	public ResponseEntity<ResponseModel> signUp(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("signUp -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Sign Up", request);
		try {
			UserModel userModel2 = signUpService.signUp(userModel);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("otp.send.success"));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Sign Up -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Sign Up -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Sign Up", request);

		if (logger.isInfoEnabled()) {
			logger.info("signUp -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/validate-otp", produces = "application/json")
	@ApiOperation(value = "Validate OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1011, message = "Please provide UserID"),
			@ApiResponse(code = 1012, message = "Invalid UserID"),
			@ApiResponse(code = 1013, message = "Please enter OTP"),
			@ApiResponse(code = 1014, message = "Invalid OTP"),
			@ApiResponse(code = 1015, message = "OTP expires"),
			@ApiResponse(code = 1016, message = "Please provide Device ID") })
	public ResponseEntity<ResponseModel> validateOTP(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("validateOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Validate OTP", request);
		try {
			UserModel userModel2 = signUpService.validateOTP(userModel);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("user.add.success"));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Validate OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Validate OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Validate OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("validateOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/resend-otp", produces = "application/json")
	@ApiOperation(value = "Resend OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1011, message = "Please provide UserID"),
			@ApiResponse(code = 1012, message = "Invalid UserID") })
	public ResponseEntity<ResponseModel> resendOTP(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("resendOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Resend OTP", request);
		try {
			UserModel userModel2 = signUpService.resendOTP(userModel);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle("otp.send.success"));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Resend OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Resend OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Resend OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("resendOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}