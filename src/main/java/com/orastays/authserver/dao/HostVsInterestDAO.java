/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.HostVsInterestEntity;

@Repository
public class HostVsInterestDAO extends GenericDAO<HostVsInterestEntity, Long> {

	private static final long serialVersionUID = 4329670121529152447L;

	public HostVsInterestDAO() {
		super(HostVsInterestEntity.class);

	}
}