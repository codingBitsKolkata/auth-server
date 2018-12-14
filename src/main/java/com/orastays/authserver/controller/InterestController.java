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
import com.orastays.authserver.model.InterestModel;
import com.orastays.authserver.model.ResponseModel;
import com.orastays.authserver.model.UserModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "interest", tags = "Interest")
public class InterestController extends BaseController {

	private static final Logger logger = LogManager.getLogger(InterestController.class);
	
	@GetMapping(value = "/fetch-interests", produces = "application/json")
	@ApiOperation(value = "Fetch Active Interests", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 203, message = "Token Expires!!!Please login to continue...") })
	public ResponseEntity<ResponseModel> fetchInterests(@RequestParam(value = "hostToken", required = true) String hostToken) {

		if (logger.isInfoEnabled()) {
			logger.info("fetchInterests -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Interests", request);
		try {
			List<InterestModel> interestModels = interestService.fetchInterests(hostToken);
			responseModel.setResponseBody(interestModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch Interests -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Interests -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Interests", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchInterests -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/add-host-interest", produces = "application/json")
	@ApiOperation(value = "Add Host Interest", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 319, message = "Session expires!!!Please Login to continue..."),
			@ApiResponse(code = 331, message = "Please Select Interest"),
			@ApiResponse(code = 332, message = "Invalid Interest") })
	public ResponseEntity<ResponseModel> addHostInterest(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("addHostInterest -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(responseModel, AuthConstant.INCOMING, "Add Host Interest", request);
		try {
			interestService.addHostInterest(userModel);
			responseModel.setResponseBody(messageUtil.getBundle("host.identity.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Add Host Interest -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Add Host Interest -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Add Host Interest", request);

		if (logger.isInfoEnabled()) {
			logger.info("addHostInterest -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
