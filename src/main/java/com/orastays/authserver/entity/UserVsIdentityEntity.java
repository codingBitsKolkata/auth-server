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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_vs_identity")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserVsIdentityEntity extends CommonEntity {
	
	private static final long serialVersionUID = -8052265380788982025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_vs_identity_id")
	@JsonProperty("userVsIdentityId")
	private Long userVsIdentityId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "identity_id", nullable = false)
	@JsonProperty("identity")
	private IdentityEntity identityEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;
	
	@Column(name = "identity_number")
	@JsonProperty("identityNumber")
	private String identityNumber;
	
	@Column(name = "file_url")
	@JsonProperty("fileUrl")
	private String fileUrl;
	
	@Override
	public String toString() {
		return Long.toString(userVsIdentityId);
	}
	
	
}
