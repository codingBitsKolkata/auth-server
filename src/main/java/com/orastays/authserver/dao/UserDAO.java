/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserEntity;

@Repository
public class UserDAO extends GenericDAO<UserEntity, Long> {

	private static final long serialVersionUID = 6568375491865686473L;

	public UserDAO() {
		super(UserEntity.class);

	}
}