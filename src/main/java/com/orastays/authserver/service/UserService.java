/**
 * @author Abhideep
 */
package com.orastays.authserver.service;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.LanguageModel;
import com.orastays.authserver.model.UserModel;

public interface UserService {

	UserModel fetchUserByID(String userId) throws FormExceptions;
	UserModel checkToken(String userToken) throws FormExceptions;
	LanguageModel checkLanguage(String languageId) throws FormExceptions;

}