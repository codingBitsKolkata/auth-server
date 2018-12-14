/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserVsLanguageEntity;

@Repository
public class UserVsLanguageDAO extends GenericDAO<UserVsLanguageEntity, Long> {

	private static final long serialVersionUID = 8218607289499611724L;

	public UserVsLanguageDAO() {
		super(UserVsLanguageEntity.class);

	}
}