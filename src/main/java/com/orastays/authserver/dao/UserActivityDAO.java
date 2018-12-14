/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserActivityEntity;

@Repository
public class UserActivityDAO extends GenericDAO<UserActivityEntity, Long> {

	private static final long serialVersionUID = 1475103608195760315L;

	public UserActivityDAO() {
		super(UserActivityEntity.class);

	}
}