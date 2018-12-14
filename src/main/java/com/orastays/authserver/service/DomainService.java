package com.orastays.authserver.service;

import java.util.List;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.DomainModel;
import com.orastays.authserver.model.UserModel;

public interface DomainService {

	List<DomainModel> fetchDomains(String userToken) throws FormExceptions;

	void addHostDomain(UserModel userModel) throws FormExceptions;

}
