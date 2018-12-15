/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class CountryModel extends CommonModel {

	@JsonProperty("countryId")
	private String countryId;
	
	@JsonProperty("countryName")
	private String countryName;
	
	@JsonProperty("countryCode")
	private String countryCode;
}
