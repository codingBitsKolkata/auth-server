/**
 * @author Abhideep
 */
package com.orastays.authserver.service;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.UserModel;

public interface SignUpService {

	UserModel signUp(UserModel userModel) throws FormExceptions;
	UserModel validateOTP(UserModel userModel) throws FormExceptions;
	UserModel resendOTP(UserModel userModel) throws FormExceptions;
}