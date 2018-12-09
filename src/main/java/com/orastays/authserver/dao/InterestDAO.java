/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.InterestEntity;

@Repository
public class InterestDAO extends GenericDAO<InterestEntity, Long> {

	private static final long serialVersionUID = -8551353105479142586L;

	public InterestDAO() {
		super(InterestEntity.class);

	}
}