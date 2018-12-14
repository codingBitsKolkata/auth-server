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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "User", tags = "User")
public class UserController extends BaseController {

	private static final Logger logger = LogManager.getLogger(SignUpController.class);
	
	@GetMapping(value = "/fetch-user-by-id", produces = "application/json")
	@ApiOperation(value = "Fetch User Details By UserID", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 311, message = "Please provide UserID"),
			@ApiResponse(code = 312, message = "Invalid UserID") })
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
			@ApiResponse(code = 319, message = "Session expires!!!Please Login to continue...") })
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
	@ApiOperation(value = "Add User Activity", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 319, message = "Session expires!!!Please Login to continue...") })
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
	
	@PostMapping(value = "/update-user-info", produces = "application/json")
	@ApiOperation(value = "Update User Info", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 319, message = "Session expires!!!Please Login to continue..."),
			@ApiResponse(code = 320, message = "Invalid Alternate Mobile Number"),
			@ApiResponse(code = 321, message = "Invalid Date Of Birth"),
			@ApiResponse(code = 322, message = "Invalid Image Format"),
			@ApiResponse(code = 323, message = "Error in Image Uploading!!! Please try after sometime...") })
	public ResponseEntity<ResponseModel> updateUserInfo(@RequestBody UserVsInfoModel userVsInfoModel) {

		if (logger.isInfoEnabled()) {
			logger.info("updateUserInfo -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.INCOMING, "Update User Info", request);
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
}