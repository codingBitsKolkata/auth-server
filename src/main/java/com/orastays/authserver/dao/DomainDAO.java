/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.DomainEntity;

@Repository
public class DomainDAO extends GenericDAO<DomainEntity, Long> {

	private static final long serialVersionUID = 8040996665305375209L;

	public DomainDAO() {
		super(DomainEntity.class);

	}
}