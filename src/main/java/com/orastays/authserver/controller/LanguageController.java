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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.LanguageModel;
import com.orastays.authserver.model.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "language", tags = "Language")
public class LanguageController extends BaseController {

	private static final Logger logger = LogManager.getLogger(LanguageController.class);
	
	@GetMapping(value = "/fetch-languages", produces = "application/json")
	@ApiOperation(value = "Fetch Active Languages", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	public ResponseEntity<ResponseModel> fetchLanguages() {

		if (logger.isInfoEnabled()) {
			logger.info("fetchLanguages -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(null, AuthConstant.INCOMING, "Fetch Languages", request);
		try {
			List<LanguageModel> languageModels = languageService.fetchLanguages();
			responseModel.setResponseBody(languageModels);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Languages -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Fetch Languages", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchLanguages -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/check-language", produces = "application/json")
	@ApiOperation(value = "Check Language", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	public ResponseEntity<ResponseModel> checkLanguage(@RequestParam(value = "languageId", required = true) String languageId) {

		if (logger.isInfoEnabled()) {
			logger.info("checkLanguage -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(languageId, AuthConstant.INCOMING, "Check Language", request);
		try {
			LanguageModel languageModel = languageService.checkLanguage(languageId);
			responseModel.setResponseBody(languageModel);
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Check Language -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Check Language -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, AuthConstant.OUTGOING, "Check Language", request);

		if (logger.isInfoEnabled()) {
			logger.info("checkLanguage -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
