/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserTypeEntity;

@Repository
public class UserTypeDAO extends GenericDAO<UserTypeEntity, Long> {

	private static final long serialVersionUID = 8653168411533971197L;

	public UserTypeDAO() {
		super(UserTypeEntity.class);

	}
}