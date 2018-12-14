/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.IdentityEntity;

@Repository
public class IdentityDAO extends GenericDAO<IdentityEntity, Long> {

	private static final long serialVersionUID = -824254354587486148L;

	public IdentityDAO() {
		super(IdentityEntity.class);

	}
}