/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import java.util.List;
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
import com.orastays.authserver.model.IdentityModel;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.UserVsIdentityModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "identity", tags = "Identity")
public class IdentityController extends BaseController {

	private static final Logger logger = LogManager.getLogger(IdentityController.class);
	
	@GetMapping(value = "/fetch-identities", produces = "application/json")
	@ApiOperation(value = "Fetch Active Identities", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token") })
	public ResponseEntity<ResponseModel> fetchIdentities(@RequestParam(value = "userToken", required = true) String userToken) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchIdentities -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Identities", request);
		try {
			List<IdentityModel> identityModels = identityService.fetchIdentities(userToken);
			responseModel.setResponseBody(identityModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch Identities -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Identities -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Identities", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchIdentities -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/add-user-identity", produces = "application/json")
	@ApiOperation(value = "Add User Identity", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 322, message = "Invalid user Token"),
			@ApiResponse(code = 327, message = "Please give Identity"),
			@ApiResponse(code = 328, message = "Invalid Identity"),
			@ApiResponse(code = 329, message = "Please give Identity Number"),
			@ApiResponse(code = 331, message = "Invalid Identity Number"),
			@ApiResponse(code = 331, message = "Error in Identity Uploading!!! Please try after sometime...") })
	public ResponseEntity<ResponseModel> addUserIdentity(@RequestBody UserVsIdentityModel userVsIdentityModel) {

		if (logger.isInfoEnabled()) {
			logger.info("addUserIdentity -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.INCOMING, "Add User Identity", request);
		try {
			identityService.addUserIdentity(userVsIdentityModel);
			responseModel.setResponseBody(messageUtil.getBundle("user.identity.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Add User Identity -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Add User Identity -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Add User Identity", request);

		if (logger.isInfoEnabled()) {
			logger.info("addUserIdentity -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
