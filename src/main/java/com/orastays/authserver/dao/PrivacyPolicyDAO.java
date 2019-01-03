/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.PrivacyPolicyEntity;

@Repository
public class PrivacyPolicyDAO extends GenericDAO<PrivacyPolicyEntity, Long> {

	private static final long serialVersionUID = 1311394555737063041L;

	public PrivacyPolicyDAO() {
		super(PrivacyPolicyEntity.class);

	}
}