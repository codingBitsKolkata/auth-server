package com.orastays.authserver.service;

import java.util.List;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.LanguageModel;
import com.orastays.authserver.model.UserModel;

public interface LanguageService {

	List<LanguageModel> fetchLanguages();
	LanguageModel checkLanguage(String languageId) throws FormExceptions;
	void addHostLanguage(UserModel userModel) throws FormExceptions;
}
