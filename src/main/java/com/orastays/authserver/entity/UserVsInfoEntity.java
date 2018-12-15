package com.orastays.authserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_vs_info")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserVsInfoEntity extends CommonEntity {

	private static final long serialVersionUID = -7481692669648220666L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_vs_info_id")
	@JsonProperty("userVsInfoId")
	private Long userVsInfoId;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "image_url")
	@JsonProperty("imageUrl")
	private String imageUrl;

	@Column(name = "alt_phno")
	@JsonProperty("altPhno")
	private String altPhno;

	@Column(name = "bio_info")
	@JsonProperty("bioInfo")
	private String bioInfo;

	@Column(name = "dob")
	@JsonProperty("dob")
	private String dob;

	@Column(name = "company_name")
	@JsonProperty("companyName")
	private String companyName;

	@Column(name = "location")
	@JsonProperty("location")
	private String location;

	@Column(name = "no_show_count")
	@JsonProperty("noShowCount")
	private String noShowCount;

	@Override
	public String toString() {
		return Long.toString(userVsInfoId);
	}

}
