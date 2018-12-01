package com.orastays.authserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_language")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LanguageEntity extends CommonEntity {

	private static final long serialVersionUID = -9206246840155646832L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private Long languageId;

	@Column(name = "description")
	private String description;

	@Override
	public String toString() {
		return Long.toString(languageId);
	}

}
