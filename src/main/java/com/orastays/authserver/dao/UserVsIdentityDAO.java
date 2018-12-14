/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.UserVsIdentityEntity;

@Repository
public class UserVsIdentityDAO extends GenericDAO<UserVsIdentityEntity, Long> {

	private static final long serialVersionUID = -7127183150001962450L;

	public UserVsIdentityDAO() {
		super(UserVsIdentityEntity.class);

	}
}