/**
 * @author Abhideep
 */
package com.orastays.authserver.service;

import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.UserActivityModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.model.UserVsInfoModel;

public interface UserService {

	UserModel fetchUserByID(String userId) throws FormExceptions;
	UserModel checkToken(String userToken) throws FormExceptions;
	UserModel fetchUserByMobileNumber(String mobileNumber, String countryId);
	UserModel fetchUserByEmail(String emailId);
	void addUserActivity(UserActivityModel userActivityModel) throws FormExceptions;
	UserModel updateUserInfo(UserVsInfoModel userVsInfoModel) throws FormExceptions;
	UserEntity validateUserByMobileNumber(String mobileNumber, String countryId);
	UserEntity validateUserByEmail(String emailId);
	void sendEmailOTP(String userToken) throws FormExceptions;
	void verifiedEmailOTP(UserModel userModel) throws FormExceptions;
	void sendMobileOTP(String userToken) throws FormExceptions;
	void verifiedMobileOTP(UserModel userModel) throws FormExceptions;
}