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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(value = "user", description = "Rest API for User", tags = "User API")
public class SignUpController extends BaseController {

	private static final Logger logger = LogManager.getLogger(SignUpController.class);
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "User Sign Up", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 301, message = "Please Enter Name"),
			@ApiResponse(code = 302, message = "Invalid Name"),
			@ApiResponse(code = 303, message = "Please Select Country"),
			@ApiResponse(code = 304, message = "Invalid Country"),
			@ApiResponse(code = 305, message = "Please Enter Mobile Number"),
			@ApiResponse(code = 306, message = "Invalid Mobile Number"),
			@ApiResponse(code = 307, message = "Mobile Number Already Registered"),
			@ApiResponse(code = 308, message = "Please Enter Email ID"),
			@ApiResponse(code = 309, message = "Invalid Email ID"),
			@ApiResponse(code = 310, message = "Email ID Already Registered") })
	public ResponseEntity<ResponseModel> signUp(@RequestBody UserModel userModel) {

		if (logger.isInfoEnabled()) {
			logger.info("signUp -- START");
		}

		ResponseModel responseModel = new ResponseModel();

		try {
			signUpService.signUp(userModel);
			responseModel.setResponseBody(messageUtil.getBundle("user.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(AuthConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}