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
@Table(name = "master_country")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CountryEntity extends CommonEntity {

	private static final long serialVersionUID = -1253317085765446285L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Long countryId;

	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Override
	public String toString() {
		return Long.toString(countryId);
	}
}
