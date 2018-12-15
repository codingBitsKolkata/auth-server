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
@Table(name = "user_vs_language")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserVsLanguageEntity extends CommonEntity {

	private static final long serialVersionUID = -6851549608108734877L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_vs_language_id")
	@JsonProperty("userVsLanguageId")
	private Long userVsLanguageId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "language_id", nullable = false)
	@JsonProperty("language")
	private LanguageEntity languageEntity;
	
	@Override
	public String toString() {
		return Long.toString(userVsLanguageId);
	}
}
