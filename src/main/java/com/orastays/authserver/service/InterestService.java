package com.orastays.authserver.service;

import java.util.List;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.InterestModel;
import com.orastays.authserver.model.UserModel;

public interface InterestService {

	List<InterestModel> fetchInterests(String userToken) throws FormExceptions;

	void addHostInterest(UserModel userModel) throws FormExceptions;

}
