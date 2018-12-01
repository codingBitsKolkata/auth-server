/**
 * @author Abhideep
 */
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
@Table(name = "master_user_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserTypeEntity extends CommonEntity {

	private static final long serialVersionUID = 4931456408266282538L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_type_id")
	private Long userTypeId;

	@Column(name = "user_type")
	private String userType;
	
	@Override
	public String toString() {
		return Long.toString(userTypeId);
	}
}
