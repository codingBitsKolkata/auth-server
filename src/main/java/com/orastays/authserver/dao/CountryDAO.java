/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.CountryEntity;

@Repository
public class CountryDAO extends GenericDAO<CountryEntity, Long> {

	private static final long serialVersionUID = -7415174767626924191L;

	public CountryDAO() {
		super(CountryEntity.class);

	}
}