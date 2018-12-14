/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserVsInfoEntity;

@Repository
public class UserVsInfoDAO extends GenericDAO<UserVsInfoEntity, Long> {


	private static final long serialVersionUID = 4145662698167916858L;

	public UserVsInfoDAO() {
		super(UserVsInfoEntity.class);

	}
}