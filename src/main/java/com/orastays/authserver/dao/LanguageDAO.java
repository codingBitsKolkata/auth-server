/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.LanguageEntity;

@Repository
public class LanguageDAO extends GenericDAO<LanguageEntity, Long> {

	private static final long serialVersionUID = 8486208321293397180L;

	public LanguageDAO() {
		super(LanguageEntity.class);

	}
}