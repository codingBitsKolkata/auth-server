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
@Table(name = "master_interest")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class InterestEntity extends CommonEntity {

	private static final long serialVersionUID = -5657464061557940063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interest_id")
	@JsonProperty("interestId")
	private Long interestId;

	@Column(name = "interest_name")
	@JsonProperty("interestName")
	private String interestName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interestEntity", cascade = { CascadeType.ALL })
	@JsonProperty("hostVsInterests")
	private List<HostVsInterestEntity> hostVsInterestEntities;

	
	@Override
	public String toString() {
		return Long.toString(interestId);
	}
}
