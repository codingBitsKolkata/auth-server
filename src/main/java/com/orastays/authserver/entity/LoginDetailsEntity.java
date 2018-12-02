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
@Table(name = "login_details")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LoginDetailsEntity extends CommonEntity {

	private static final long serialVersionUID = 5990216311289535507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_id")
	private Long loginId;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Column(name = "ip")
	private String ip;

	@Column(name = "device_id")
	private String deviceId;
	
	@Column(name = "session_token")
	private String sessionToken;

	@Override
	public String toString() {
		return Long.toString(loginId);
	}

}
