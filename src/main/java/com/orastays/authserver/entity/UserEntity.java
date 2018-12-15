/**
 * @author Abhideep
 */
package com.orastays.authserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_user")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends CommonEntity {

	private static final long serialVersionUID = -2408742872629982093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonProperty("userId")
	private Long userId;

	@Column(name = "mobile_number")
	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@Column(name = "email_id")
	@JsonProperty("emailId")
	private String emailId;

	@Column(name = "is_email_verified")
	@JsonProperty("isEmailVerified")
	private String isEmailVerified;

	@Column(name = "is_mobile_verified")
	@JsonProperty("isMobileVerified")
	private String isMobileVerified;

	@Column(name = "otp")
	@JsonProperty("otp")
	private String otp;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "country_id", nullable = false)
	@JsonProperty("country")
	private CountryEntity countryEntity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("userVsTypes")
	private List<UserVsTypeEntity> userVsTypeEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("userActivitys")
	private List<UserActivityEntity> userActivityEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("userVsLanguages")
	private List<UserVsLanguageEntity> userVsLanguageEntities;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("userVsInfo")
	private UserVsInfoEntity userVsInfoEntity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("userVsIdentitys")
	private List<UserVsIdentityEntity> userVsIdentityEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("loginDetailss")
	private List<LoginDetailsEntity> loginDetailsEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("hostVsInterests")
	private List<HostVsInterestEntity> hostVsInterestEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("hostVsDomains")
	private List<HostVsDomainEntity> hostVsDomainEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = { CascadeType.ALL })
	@JsonProperty("hostVsAccount")
	private HostVsAccountEntity hostVsAccountEntity;

	@Override
	public String toString() {
		return Long.toString(userId);
	}
}
