/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.UserActivityModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.model.UserVsInfoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
@Api(value = "User", tags = "User")
public class UserController extends BaseController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@GetMapping(value = "/fetch-user-by-id", produces = "application/json")
	@ApiIgnore
	@ApiOperation(value = "Fetch User Details By UserID", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1011, message = "Please provide UserID"),
			@ApiResponse(code = 1012, message = "Invalid UserID")})
	
	public ResponseEntity<ResponseModel> fetchUserByID(@RequestParam(value = "userId", required = true) String userId) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByID -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userId, AuthConstant.INCOMING, "Fetch User Details By UserID", request);
		try {
			UserModel userModel2 = userService.fetchUserByID(userId);
			responseModel.setResponseBody(userModel2);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch User Details By UserID -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch User Details By UserID -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch User Details By UserID", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchUserByID -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/check-token", produces = "application/json")
	@ApiOperation(value = "Check Token", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive")})
	public ResponseEntity<ResponseModel> checkToken(@RequestParam(value = "userToken", required = true) String userToken) {

		if (logger.isInfoEnabled()) {
			logger.info("checkToken -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userToken, AuthConstant.INCOMING, "Check Token", request);
		try {
			UserModel userModel = userService.checkToken(userToken);
			responseModel.setResponseBody(userModel);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Check Token -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Check Token -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Check Token", request);

		if (logger.isInfoEnabled()) {
			logger.info("checkToken -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/add-user-activity", produces = "application/json")
	@ApiIgnore
	@ApiOperation(value = "Add User Activity", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!!Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive")})
	public ResponseEntity<ResponseModel> addUserActivity(@RequestBody UserActivityModel userActivityModel) {

		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.INCOMING, "Add User Activity", request);
		try {
			userService.addUserActivity(userActivityModel);
			responseModel.setResponseBody(messageUtil.getBundle("user.activity.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Add User Activity -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Add User Activity -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Add User Activity", request);

		if (logger.isInfoEnabled()) {
			logger.info("addUserActivity -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/update-user-info", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiIgnore
	@ApiOperation(value = "Update User Info", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive"),
			@ApiResponse(code = 1019, message = "Invalid Alternate Mobile Number"),
			@ApiResponse(code = 1020, message = "Invalid Date Of Birth"),
			@ApiResponse(code = 1021, message = "Invalid Image Format"),
			@ApiResponse(code = 1022, message = "Error in Image Uploading!!! Please try after sometime..."),
			@ApiResponse(code = 1002, message = "Please Select Country"),
			@ApiResponse(code = 1003, message = "Invalid Country")})
	public ResponseEntity<ResponseModel> updateUserInfo(@ModelAttribute UserVsInfoModel userVsInfoModel) {

		if (logger.isInfoEnabled()) {
			logger.info("updateUserInfo -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userVsInfoModel, AuthConstant.INCOMING, "Update User Info", request);
		try {
			UserModel userModel = userService.updateUserInfo(userVsInfoModel);
			responseModel.setResponseBody(userModel);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Update User Info -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Update User Info -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Update User Info", request);

		if (logger.isInfoEnabled()) {
			logger.info("updateUserInfo -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/send-email-otp", produces = "application/json")
	@ApiOperation(value = "Send Email OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive")})
	public ResponseEntity<ResponseModel> sendEmailOTP(@RequestParam(value = "userToken", required = true) String userToken) {

		if (logger.isInfoEnabled()) {
			logger.info("sendEmailOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userToken, AuthConstant.INCOMING, "Send Email OTP", request);
		try {
			userService.sendEmailOTP(userToken);
			responseModel.setResponseBody(messageUtil.getBundle("otp.send.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Send Email OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Send Email OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Send Email OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("sendEmailOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/verify-email-otp", produces = "application/json")
	@ApiOperation(value = "Verify Email OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive"),
			@ApiResponse(code = 1013, message = "Please enter OTP"),
			@ApiResponse(code = 1014, message = "Invalid OTP"),
			@ApiResponse(code = 1015, message = "OTP expires"),
			@ApiResponse(code = 1032, message = "Email Already Verified")})
	public ResponseEntity<ResponseModel> verifyEmailOTP(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("verifyEmailOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Verify Email OTP", request);
		try {
			userService.verifiedEmailOTP(userModel);
			responseModel.setResponseBody(messageUtil.getBundle("email.verified.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Verify Email OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Verify Email OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Verify Email OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("verifyEmailOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/send-mobile-otp", produces = "application/json")
	@ApiOperation(value = "Send Mobile OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive")})
	public ResponseEntity<ResponseModel> sendMobileOTP(@RequestParam(value = "userToken", required = true) String userToken) {

		if (logger.isInfoEnabled()) {
			logger.info("sendMobileOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userToken, AuthConstant.INCOMING, "Send Mobile OTP", request);
		try {
			userService.sendMobileOTP(userToken);
			responseModel.setResponseBody(messageUtil.getBundle("otp.send.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Send Mobile OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Send Mobile OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Send Mobile OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("sendMobileOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/verify-mobile-otp", produces = "application/json")
	@ApiOperation(value = "Verify Mobile OTP", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 1010, message = "User is Inactive"),
			@ApiResponse(code = 1013, message = "Please enter OTP"),
			@ApiResponse(code = 1014, message = "Invalid OTP"),
			@ApiResponse(code = 1015, message = "OTP expires"),
			@ApiResponse(code = 1033, message = "Mobile Number Already Verified")})
	public ResponseEntity<ResponseModel> verifyMobileOTP(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("verifyMobileOTP -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Verify Mobile OTP", request);
		try {
			userService.verifiedMobileOTP(userModel);
			responseModel.setResponseBody(messageUtil.getBundle("mobile.verified.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Verify Mobile OTP -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Verify Mobile OTP -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Verify Mobile OTP", request);

		if (logger.isInfoEnabled()) {
			logger.info("verifyMobileOTP -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/check-email", produces = "application/json")
	@ApiOperation(value = "Check Email", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1009, message = "Email ID Already Registered") })
	public ResponseEntity<ResponseModel> checkEmail(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("checkEmail -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Check Email", request);
		try {
			UserEntity userEntity = userService.validateUserByEmail(userModel.getEmailId());
			if(Objects.nonNull(userEntity)) {
				Map<String, Exception> exceptions = new LinkedHashMap<>();
				exceptions.put(messageUtil.getBundle("user.email.present.code"), new Exception(messageUtil.getBundle("user.email.present.message")));
				throw new FormExceptions(exceptions);
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Check Email -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Check Email -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Check Email", request);

		if (logger.isInfoEnabled()) {
			logger.info("checkEmail -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/check-mobile", produces = "application/json")
	@ApiOperation(value = "Check Mobile", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 1006, message = "Mobile Number Already Registered") })
	public ResponseEntity<ResponseModel> checkMobile(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("checkMobile -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userModel, AuthConstant.INCOMING, "Check Mobile", request);
		try {
			UserEntity userEntity = userService.validateUserByMobileNumber(userModel.getMobileNumber(), userModel.getCountryModel().getCountryId());
			if(Objects.nonNull(userEntity)) {
				Map<String, Exception> exceptions = new LinkedHashMap<>();
				exceptions.put(messageUtil.getBundle("user.mobile.present.code"), new Exception(messageUtil.getBundle("user.mobile.present.message")));
				throw new FormExceptions(exceptions);
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Check Mobile -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (NullPointerException e) {
			if (logger.isInfoEnabled()) {
				logger.info("NullPointerException in Check Mobile -- "+Util.errorToString(e));
			}
			
			responseModel.setResponseCode(messageUtil.getBundle("user.mobile.null.code"));
			responseModel.setResponseMessage(messageUtil.getBundle("user.mobile.null.message"));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Check Mobile -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Check Mobile", request);

		if (logger.isInfoEnabled()) {
			logger.info("checkMobile -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}