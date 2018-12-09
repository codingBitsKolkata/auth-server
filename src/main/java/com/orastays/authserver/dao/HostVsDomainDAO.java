/**
 * @author Abhideep
 */
package com.orastays.authserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.authserver.entity.HostVsDomainEntity;

@Repository
public class HostVsDomainDAO extends GenericDAO<HostVsDomainEntity, Long> {

	private static final long serialVersionUID = -4732883871353722129L;

	public HostVsDomainDAO() {
		super(HostVsDomainEntity.class);

	}
}