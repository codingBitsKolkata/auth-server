/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.LoginDetailsEntity;

@Repository
public class LoginDetailsDAO extends GenericDAO<LoginDetailsEntity, Long> {

	private static final long serialVersionUID = -1487994452977894295L;

	public LoginDetailsDAO() {
		super(LoginDetailsEntity.class);

	}
}