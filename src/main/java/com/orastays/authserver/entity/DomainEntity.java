package com.orastays.authserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_domain")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class DomainEntity extends CommonEntity {

	private static final long serialVersionUID = -708781883850481967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "domain_id")
	@JsonProperty("domainId")
	private Long domainId;

	@Column(name = "domain_name")
	@JsonProperty("domainName")
	private String domainName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domainEntity", cascade = { CascadeType.ALL })
	@JsonProperty("HostVsDomains")
	private List<HostVsDomainEntity> hostVsDomainEntities;

	@Override
	public String toString() {
		return Long.toString(domainId);
	}
}
