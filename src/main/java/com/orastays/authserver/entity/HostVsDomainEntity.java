package com.orastays.authserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "host_vs_domain")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class HostVsDomainEntity extends CommonEntity {

	private static final long serialVersionUID = 1345250447701916597L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "host_dom_id")
	@JsonProperty("hostDomId")
	private Long hostDomId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "domain_id", nullable = false)
	@JsonProperty("domain")
	private DomainEntity domainEntity;

	@Override
	public String toString() {
		return Long.toString(hostDomId);
	}
}
