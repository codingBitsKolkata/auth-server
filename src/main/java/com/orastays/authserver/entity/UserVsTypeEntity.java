/**
 * @author Abhideep
 */
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
@Table(name = "user_vs_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserVsTypeEntity extends CommonEntity {

	private static final long serialVersionUID = 8527950846365621697L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_vs_type_id")
	@JsonProperty("userVsTypeId")
	private Long userVsTypeId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_type_id", nullable = false)
	@JsonProperty("userType")
	private UserTypeEntity userTypeEntity;
	
	@Override
	public String toString() {
		return Long.toString(userVsTypeId);
	}
}
