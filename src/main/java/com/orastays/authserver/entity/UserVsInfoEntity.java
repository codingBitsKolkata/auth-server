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
	private Long userVsInfoId;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "alt_phno")
	private String altPhno;

	@Column(name = "bio_info")
	private String bioInfo;

	@Column(name = "dob")
	private String dob;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "location")
	private String location;

	@Column(name = "no_show_count")
	private String noShowCount;

	@Override
	public String toString() {
		return Long.toString(userVsInfoId);
	}

}
