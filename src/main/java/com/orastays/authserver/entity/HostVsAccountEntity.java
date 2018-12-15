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
@Table(name = "host_vs_account")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class HostVsAccountEntity extends CommonEntity {

	private static final long serialVersionUID = 169018712547106587L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "host_vs_account_id")
	@JsonProperty("hostVsAccountId")
	private Long hostVsAccountId;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;

	@Column(name = "account_number")
	@JsonProperty("accountNumber")
	private String accountNumber;

	@Column(name = "account_holder_name")
	@JsonProperty("accountHolderName")
	private String accountHolderName;

	@Column(name = "account_type")
	@JsonProperty("accountType")
	private String accountType;

	@Column(name = "bank_name")
	@JsonProperty("bankName")
	private String bankName;

	@Column(name = "branch_name")
	@JsonProperty("branchName")
	private String branchName;

	@Column(name = "ifsc_code")
	@JsonProperty("ifscCode")
	private String ifscCode;

	@Override
	public String toString() {
		return Long.toString(hostVsAccountId);
	}

}
