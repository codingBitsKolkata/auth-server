/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.PrivacyPolicyModel;
import com.orastays.authserver.model.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api")
@Api(value = "Privacy Policy", tags = "Privacy Policy")
public class PrivacyPolicyController extends BaseController {

	private static final Logger logger = LogManager.getLogger(PrivacyPolicyController.class);
	
	@GetMapping(value = "/fetch-privacy-policy", produces = "application/json")
	@ApiOperation(value = "Fetch Privacy Policy", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	public ResponseEntity<ResponseModel> fetchPrivacyPolicy() {

		if (logger.isInfoEnabled()) {
			logger.info("fetchPrivacyPolicy -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(null, AuthConstant.INCOMING, "Fetch Countries", request);
		try {
			PrivacyPolicyModel privacyPolicyModel = privacyPolicyService.fetchPrivacyPolicy();
			responseModel.setResponseBody(privacyPolicyModel);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in fetchPrivacyPolicy -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Privacy Policy", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchPrivacyPolicy -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
