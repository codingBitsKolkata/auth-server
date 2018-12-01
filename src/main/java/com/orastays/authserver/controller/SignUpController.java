/**
 * @author Abhideep
 */
package com.orastays.authserver.controller;

import java.security.Principal;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.authserver.exceptions.FormExceptions;
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
	
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ADMIN')")
	@RequestMapping(value = "/add-user", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Add User By Admin", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 301, message = "Please give Employee Id"),
			@ApiResponse(code = 302, message = "Employee Already Present"),
			@ApiResponse(code = 303, message = "Please Select A Role"),
			@ApiResponse(code = 304, message = "Invalid Role") })
	public ResponseModel addUser(@RequestHeader("Authorization") String authorization, @RequestBody UserModel userModel, Principal user) {

		if (logger.isInfoEnabled()) {
			logger.info("addUser -- START");
		}

		ResponseModel responseModel = new ResponseModel();

		/*try {
			userService.addUser(authorization, userModel, user);
			responseModel.setResponse(messageUtil.getBundle("user.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(GitathonConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(GitathonConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setResponseCode(messageUtil.getBundle(GitathonConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(GitathonConstant.COMMON_ERROR_MESSAGE));
		}

		Util.printLog(responseModel, GitathonConstant.OUTGOING, "Add User");*/

		if (logger.isInfoEnabled()) {
			logger.info("addUser -- END");
		}

		return responseModel;
	}
}