/**
 * @author Abhideep
 */
package com.orastays.authserver.service;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.UserModel;

public interface LoginService {

	UserModel validateLogin(UserModel userModel) throws FormExceptions;

	UserModel validateLoginOTP(UserModel userModel) throws FormExceptions;

	UserModel fetchInactiveUser(UserModel userModel) throws FormExceptions;

	void validateLogout(String userToken) throws FormExceptions;

}
