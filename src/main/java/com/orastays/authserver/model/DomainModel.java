/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import java.util.List;

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
public class DomainModel extends CommonModel {

	@JsonProperty("DomainId")
	private String DomainId;
	
	@JsonProperty("DomainName")
	private String DomainName;
	
	@JsonProperty("hostVsDomains")
	private List<HostVsDomainModel> hostVsDomainModels;

}