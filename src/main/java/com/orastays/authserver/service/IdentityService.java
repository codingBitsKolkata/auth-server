package com.orastays.authserver.service;

import java.util.List;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.IdentityModel;
import com.orastays.authserver.model.UserVsIdentityModel;

public interface IdentityService {

	List<IdentityModel> fetchIdentities(String userToken) throws FormExceptions;

	void addUserIdentity(UserVsIdentityModel userVsIdentityModel) throws FormExceptions;

}