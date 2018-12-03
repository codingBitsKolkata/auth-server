package com.orastays.authserver.service;

import java.util.List;

import com.orastays.authserver.model.CountryModel;

public interface CountryService {

	List<CountryModel> fetchCountries();

}
