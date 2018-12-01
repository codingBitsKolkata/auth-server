/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CountryModel extends CommonModel {

	private String countryId;
	private String countryName;
	private String countryCode;
}
