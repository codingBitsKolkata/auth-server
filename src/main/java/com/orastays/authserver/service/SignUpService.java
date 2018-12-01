/**
 * @author Abhideep
 */
package com.orastays.authserver.service;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.UserModel;

public interface SignUpService {

	void signUp(UserModel userModel) throws FormExceptions;

	UserModel fetchUserByMobileNumber(String mobileNumber);

	UserModel fetchUserByEmail(String emailId);

}
