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
@Table(name = "login_details")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LoginDetailsEntity extends CommonEntity {

	private static final long serialVersionUID = 5990216311289535507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_id")
	@JsonProperty("loginId")
	private Long loginId;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("users")
	private UserEntity userEntity;

	@Column(name = "ip")
	@JsonProperty("ip")
	private String ip;

	@Column(name = "device_id")
	@JsonProperty("deviceId")
	private String deviceId;
	
	@Column(name = "session_token")
	@JsonProperty("sessionToken")
	private String sessionToken;

	@Override
	public String toString() {
		return Long.toString(loginId);
	}

}
