/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserVsTypeEntity;

@Repository
public class UserVsTypeDAO extends GenericDAO<UserVsTypeEntity, Long> {

	private static final long serialVersionUID = 3329868190090221071L;

	public UserVsTypeDAO() {
		super(UserVsTypeEntity.class);

	}
}
